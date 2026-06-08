package com.example.demo.service.publisher;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.publisher.PublisherRequest;
import com.example.demo.dto.publisher.PublisherResponse;

public interface PublisherService {
    PublisherResponse createPublisher(PublisherRequest request);
    PublisherResponse getPublisherById(Integer id);

    PageResponse<PublisherResponse> getAllPublishers(int page, int size);

    PublisherResponse updatePublisher(Integer id, PublisherRequest request);

    void deletePublisherById(Integer id);
}
