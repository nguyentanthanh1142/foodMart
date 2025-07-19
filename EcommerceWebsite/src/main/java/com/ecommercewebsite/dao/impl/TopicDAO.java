package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.ITopicDAO;
import com.ecommercewebsite.mapper.TopicMapper;
import com.ecommercewebsite.model.TopicModel;
import com.ecommercewebsite.paging.Pageble;

public class TopicDAO extends AbstractDAO<TopicModel> implements ITopicDAO {

	@Override
	public List<TopicModel> findAll(Pageble pageble) {

		StringBuilder sql = new StringBuilder("SELECT * FROM topic");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new TopicMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM topic";
		return count(sql);
	}

	@Override
	public Long save(TopicModel topic) {
		StringBuilder sql = new StringBuilder(
				"insert into topic (name,slug,metadesc,metakey,showfooter,status,createdAt,createdBy,modifiedAt,modifiedBy) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), topic.getName(), topic.getSlug(), topic.getMetadesc(), topic.getMetakey(),
				topic.getShowFooter(), topic.getStatus(), topic.getCreatedAt(), topic.getCreatedBy(),
				topic.getModifiedAt(), topic.getModifiedBy());
	}

	@Override
	public TopicModel findOne(Long topicId) {
		String sql = "SELECT * FROM topic where id = ?";

		List<TopicModel> topic = query(sql, new TopicMapper(), topicId);

		return topic.isEmpty() ? null : topic.get(0);
	}

	@Override
	public void update(TopicModel updateTopic) {
		StringBuilder sql = new StringBuilder("UPDATE topic SET name = ?, slug = ?,");
		sql.append(" metakey = ?, metadesc = ?, showfooter = ?, status = ?,");
		sql.append(" createdat = ?, createdby = ?, modifiedat = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateTopic.getName(), updateTopic.getSlug(), updateTopic.getMetakey(),
				updateTopic.getMetadesc(), updateTopic.getShowFooter(), updateTopic.getStatus(),
				updateTopic.getCreatedAt(), updateTopic.getCreatedBy(), updateTopic.getModifiedAt(),
				updateTopic.getModifiedBy(), updateTopic.getId());
	}

	@Override
	public TopicModel findBySlug(String slug) {
		String sql = "SELECT * FROM topic where slug = ?";
		List<TopicModel> topic = query(sql, new TopicMapper(), slug);
		return topic.isEmpty() ? null : topic.get(0);
	}

	@Override
	public List<TopicModel> findAll() {
		String sql = "SELECT * FROM topic";
		List<TopicModel> topics = query(sql, new TopicMapper());
		return topics;
	}

	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM topic where id in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append(ids[i] + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			update(sql.toString());
		}
	}

	@Override
	public List<TopicModel> findTopicShowFooter() {
		String sql = "SELECT * FROM topic where status = 1 and showfooter = 1";
		return query(sql, new TopicMapper());
	}

}
