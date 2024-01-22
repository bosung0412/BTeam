package kr.co.ict.project.login.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ict.project.login.entity.FAQ;
import kr.co.ict.project.login.repository.FAQRepository;

@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    public java.util.List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    public FAQ getFAQById(Long id) {
        return faqRepository.findById(id).orElse(null);
    }

    public void saveFAQ(FAQ faq) {
        faq.setRegDate((new Date()));
        faqRepository.save(faq);
    }

    public void deleteFAQ(Long id) {
        faqRepository.deleteById(id);
    }

}
