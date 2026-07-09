 package com.anapoornabackend.demo.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.anapoornabackend.demo.entity.Donation;
import com.anapoornabackend.demo.service.impl.DonationService;

@Controller
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationService service;

    // Open Donation Form
    @GetMapping("/add")
    public String addPage(Model model) {

        model.addAttribute("donation", new Donation());

        return "donate";
    }

    // Save Donation
    @PostMapping("/save")
    public String save(@ModelAttribute Donation donation,
                       @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {

            String fileName = imageFile.getOriginalFilename();

            String uploadDir = "C:/Users/acer/OneDrive/Desktop/internproject/inteernship/anapoorna/anapoornabackend/uploads/";

            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            imageFile.transferTo(new File(uploadDir + fileName));

            donation.setImageName(fileName);
        }

        donation.setDonationDate(LocalDate.now());

        service.save(donation);

        return "redirect:/donation/list";
    }
    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("list", service.getAll());

        return "donation-list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword,
                         Model model) {

        model.addAttribute("list", service.searchByDonorName(keyword));

        return "donation-list";
    }


    // Edit Donation
    @GetMapping("/edit/{id}")
    public String editDonation(@PathVariable Long id, Model model) {

        Donation donation = service.getById(id);

        model.addAttribute("donation", donation);

        return "donate";
    }

    // Delete Donation
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "redirect:/donation/list";
    }

}