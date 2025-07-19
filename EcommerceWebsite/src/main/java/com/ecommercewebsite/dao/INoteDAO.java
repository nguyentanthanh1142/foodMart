package com.ecommercewebsite.dao;

import java.util.List;

import com.ecommercewebsite.model.NoteModel;
import com.ecommercewebsite.paging.Pageble;

public interface INoteDAO extends GenericDAO<NoteModel>{

	public List<NoteModel> findAll(Pageble pageble);
	public List<NoteModel> findAllByUser(Pageble pageble);
	public List<NoteModel>findNoteBetween(String to,String from,Pageble pageble);
	public Long save(NoteModel note);
	public NoteModel findById(Long id);
	public void delete(long[] ids);
	public int getTotalItem();
	public int getTotalItemBW(String to, String from) ;
}
