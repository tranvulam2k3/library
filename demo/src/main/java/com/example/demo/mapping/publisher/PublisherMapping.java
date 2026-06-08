package com.example.demo.mapping.publisher;

import com.example.demo.dto.publisher.PublisherRequest;
import com.example.demo.dto.publisher.PublisherResponse;
import com.example.demo.entity.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapping {

    public PublisherResponse toPublisherResponse(Publisher publisher) {
        if (publisher == null) {
            return null;
        }

        PublisherResponse response = new PublisherResponse();
        response.setPublisherId(publisher.getPublisherId());
        response.setPublisherName(publisher.getPublisherName());
        response.setEmail(publisher.getEmail());
        response.setPhone(publisher.getPhone());
        response.setAddress(publisher.getAddress());
        response.setCreatedAt(publisher.getCreatedAt());
        response.setUpdatedAt(publisher.getUpdatedAt());

        return response;
    }

    public Publisher toPublisher(PublisherRequest request) {
        if (request == null) {
            return null;
        }

        Publisher publisher = new Publisher();
        publisher.setPublisherName(request.getPublisherName());
        publisher.setEmail(request.getEmail());
        publisher.setPhone(request.getPhone());
        publisher.setAddress(request.getAddress());

        return publisher;
    }

    public void updatePublisherFromRequest(PublisherRequest request, Publisher publisher) {
        if (request == null || publisher == null){
            return;
        }

        publisher.setPublisherName(request.getPublisherName());
        publisher.setEmail(request.getEmail());
        publisher.setPhone(request.getPhone());
        publisher.setAddress(request.getAddress());
    }
}
