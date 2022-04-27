package com.dxc.socialmedia.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileServiceImplementation implements FileService {
	private final Path root = Paths.get("C:\\Users\\Audrey Wong\\Desktop\\socialmedia\\viewSocialMedia\\src\\assets\\videos");
	  
	//create a folder at start of project
	@Override
	  public void init() {
	    try {
	      Files.createDirectory(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }
	  @Override
	  public String save(MultipartFile file) {
	    try {
	    	Path checkPath = this.root.resolve(file.getOriginalFilename());
	    	String[] arrSplit = file.getOriginalFilename().split("\\."); 
    		String changeName="";
	    	int count =1;
	    	while(Files.exists(checkPath)) {
	    		changeName= arrSplit[0]+"copy"+count+"."+arrSplit[1];
	    		checkPath= this.root.resolve(this.root.resolve(changeName));
	    		count ++;
	    	}
	    	System.out.println(checkPath);
	      Files.copy(file.getInputStream(), this.root.resolve(checkPath)); // resolve to "./filename.mp4"
	      return changeName;
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
	  @Override
	  public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());
	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }
	  @Override
	  public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }
	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	@Override
	public void delete(String filename ) {
		// TODO Auto-generated method stub
		String pathString="C:\\Users\\Audrey Wong\\Desktop\\socialmedia\\viewSocialMedia\\src\\assets\\videos\\"+filename;
		Path location = Paths.get(pathString);
		
		try {
            // Delete file or directory
            Files.delete(location);
            System.out.println("File or directory deleted successfully");
        } catch (NoSuchFileException ex) {
            System.out.printf("No such file or directory: %s\n", location);
        } catch (DirectoryNotEmptyException ex) {
            System.out.printf("Directory %s is not empty\n", location);
        } catch (IOException ex) {
            System.out.println(ex);
        }
	}
}