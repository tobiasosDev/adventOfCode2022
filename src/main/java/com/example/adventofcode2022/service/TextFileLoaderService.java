package com.example.adventofcode2022.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextFileLoaderService {
    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    ResourceLoader resourceLoader;

    public String loadTextFile(String fileName) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + fileName);
            InputStream inputStream = resource.getInputStream();
            return new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("Error loading file: " + fileName, e);
            return null;
        }
    }

    public List<String> loadTextFileAsListByNewLine(String fileName) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + fileName);
            InputStream inputStream = resource.getInputStream();
            return new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8)
                    .lines().collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Error loading file: " + fileName, e);
            return null;
        }
    }
}
