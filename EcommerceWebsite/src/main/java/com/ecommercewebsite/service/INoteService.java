package com.ecommercewebsite.service;

import java.util.List;

import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.paging.Pageble;

public interface INoteService {
	public List<NoteModel> findAll(Pageble pageble);
	public List<NoteModel> findAllByUser(Pageble pageble);
	public NoteModel save(NoteModel note);
	public int getTotalItem();
	public int getTotalItemBW(String to, String from); 
	public List<NoteModel>findNoteBetween(String to,String from,Pageble pageble);
	public void delete(long[] ids);
}
