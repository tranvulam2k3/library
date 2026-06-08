package com.example.demo.service.publisher;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.publisher.PublisherRequest;
import com.example.demo.dto.publisher.PublisherResponse;
import com.example.demo.entity.Publisher;
import com.example.demo.enums.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.mapping.publisher.PublisherMapping;
import com.example.demo.repository.PublisherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PublisherServiceImpl implements PublisherService {

    PublisherRepository publisherRepository;
    PublisherMapping publisherMapping;

    // Create
    @Override
    public PublisherResponse createPublisher(PublisherRequest request) {
        if (publisherRepository.existsPublisherByPublisherName(request.getPublisherName())) {
            throw new AppException(ErrorCode.PUBLISHER_ALREADY_EXISTS, "Publisher name already exists");
        }

        Publisher publisher = publisherMapping.toPublisher(request);
        return publisherMapping.toPublisherResponse(publisherRepository.save(publisher));
    }

    // Read by ID
    @Override
    public PublisherResponse getPublisherById(Integer id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PUBLISHER_NOT_FOUND, "Publisher not found with ID: " + id));
        return publisherMapping.toPublisherResponse(publisher);
    }

    // Read All
    @Override
    public PageResponse<PublisherResponse> getAllPublishers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Publisher> publisherPage = publisherRepository.findAll(pageable);

        List<PublisherResponse> responses = new ArrayList<>();
        for (Publisher publisher : publisherPage.getContent()) {
            responses.add(publisherMapping.toPublisherResponse(publisher));
        }

        PageResponse<PublisherResponse> pageResponse = new PageResponse<>();
        pageResponse.setItems(responses);
        pageResponse.setPage(publisherPage.getNumber());
        pageResponse.setSize(publisherPage.getSize());
        pageResponse.setTotalItems(publisherPage.getTotalElements());
        pageResponse.setTotalPages(publisherPage.getTotalPages());

        return pageResponse;
    }

    // Update
    @Override
    public PublisherResponse updatePublisher(Integer id, PublisherRequest request) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.PUBLISHER_NOT_FOUND, "Publisher not found with ID: " + id));
        publisherMapping.updatePublisherFromRequest(request, publisher);
        return publisherMapping.toPublisherResponse(publisherRepository.save(publisher));
    }

    // Delete
    @Override
    public void deletePublisherById(Integer id) {
        if (!publisherRepository.existsById(id)) {
            throw new AppException(ErrorCode.PUBLISHER_NOT_FOUND, "Publisher not found with ID: " + id);
        }

        publisherRepository.deleteById(id);
    }
}
