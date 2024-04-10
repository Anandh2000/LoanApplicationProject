package com.dualtricks.assignment.controller;

import com.dualtricks.assignment.entity.ClientDetails;
import com.dualtricks.assignment.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientDetailsController {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @GetMapping("/client-details")
    public String showClientDetailsPage(@RequestParam("username") String username, Model model) {
        model.addAttribute("username", username);
        return "client-details";
    }

    @PostMapping("/submit_details")
    public String submitDetails(@RequestParam("username") String username,
                                @RequestParam("industry") String industry,
                                @RequestParam("service") String service,
                                @RequestParam("growth") String growth,
                                @RequestParam("timeline") String timeline,
                                @RequestParam("description") String description) {
        // Create a new ClientDetails object
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setUserName(username);
        clientDetails.setIndustry(industry);
        clientDetails.setService(service);
        clientDetails.setGrowth(growth);
        clientDetails.setTimeline(timeline);
        clientDetails.setDescription(description);

        // Save the client details to the database
        clientDetailsRepository.save(clientDetails);

        // Redirect to a thank you page after successful submission
        return "redirect:/clientdetails-success";
    }

    @GetMapping("/clientdetails-success")
    public String showThankYouPage() {
        return "clientdetails-success";
    }

    @GetMapping("/client-list")
    public String getClientDetails(Model model) {

        List<ClientDetails> clients = clientDetailsRepository.findAll();
       model.addAttribute("clients", clients);
        return "client-list";
    }



}
