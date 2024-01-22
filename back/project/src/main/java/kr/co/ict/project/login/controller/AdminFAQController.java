package kr.co.ict.project.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.project.login.entity.FAQ;
import kr.co.ict.project.login.service.FAQService;

@RestController
@RequestMapping("/admin/faqs")
public class AdminFAQController {
    @Autowired
    private FAQService faqService;

    @GetMapping("/list")
    public ResponseEntity<List<FAQ>> getFAQs() {
        List<FAQ> faqs = faqService.getAllFAQs();
        System.out.println(faqs);
        return ResponseEntity.ok(faqs);
    }

    @GetMapping("/{id}")
    public String getFAQ(@PathVariable Long id, Model model) {
        FAQ faq = faqService.getFAQById(id);
        model.addAttribute("faq", faq);
        return "admin/faq";
    }

    @GetMapping("/create")
    public String createFAQForm(Model model) {
        model.addAttribute("faq", new FAQ());
        return "admin/create-faq";
    }

    @PostMapping("/create")
    public String createFAQ(@ModelAttribute FAQ faq) {
        faqService.saveFAQ(faq);
        return "redirect:/admin/faqs";
    }

    @GetMapping("/{id}/delete")
    public String deleteFAQ(@PathVariable Long id) {
        faqService.deleteFAQ(id);
        return "redirect:/admin/faqs";
    }
}
