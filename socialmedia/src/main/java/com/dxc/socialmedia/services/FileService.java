package com.dxc.socialmedia.services;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public void init();
	
	public String save(MultipartFile file);

	public Resource load(String filename);

	public void deleteAll();
	
	public void delete(String filelocation);

	public Stream<Path> loadAll();

}
