package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.repositories.PublicationRepository;

@Service
public class PublicationService {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	public List<Publication> getPublications() {
		List<Publication> publication = new ArrayList<Publication>();
		publicationRepository.findAll().forEach(publication::add);
		return publication;
	}

	public Publication getPublication(Long id) {
		return publicationRepository.findOne(id);
	}

	public void addPublication(Publication publication) {
		publicationRepository.save(publication);
	}

	public void deletePublication(Long id) {
		publicationRepository.delete(id);
	}

	public List<Publication> getPublicationsForUser(User user) {
		List<Publication> posts = publicationRepository.findAllByUser(user);
		return posts;
	}
	
}
