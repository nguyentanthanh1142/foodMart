package com.ecommercewebsite.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.ecommercewebsite.dao.INoteDAO;
import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.paging.Pageble;
import com.ecommercewebsite.service.INoteService;

public class NoteService implements INoteService {

	@Inject
	private INoteDAO noteDAO;

	@Override
	public List<NoteModel> findAll(Pageble pageble) {
		// TODO Auto-generated method stub
		return noteDAO.findAll(pageble);
	}

	@Override
	public List<NoteModel> findAllByUser(Pageble pageble) {
		// TODO Auto-generated method stub
		return noteDAO.findAllByUser(pageble);
	}

	@Override
	public NoteModel save(NoteModel note) {
		Long id = noteDAO.save(note);

		// TODO Auto-generated method stub
		return noteDAO.findById(id);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return noteDAO.getTotalItem();
	}

	@Override
	public List<NoteModel> findNoteBetween(String to, String from, Pageble pageble) {
		// TODO Auto-generated method stub
		return noteDAO.findNoteBetween(to,from,pageble);
	}

	@Override
	public int getTotalItemBW(String to, String from) {
		// TODO Auto-generated method stub
		return noteDAO.getTotalItemBW(to, from);
	}

	@Override
	public void delete(long[] ids) {
		noteDAO.delete(ids);
		
	}

}
