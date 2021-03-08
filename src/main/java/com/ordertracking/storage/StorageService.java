package com.ordertracking.storage;



import com.ordertracking.domain.OrderTracking;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

	void init();

	Path load(String filename);

	void deleteAll();

	OrderTracking storeTracking(MultipartFile file);

}
