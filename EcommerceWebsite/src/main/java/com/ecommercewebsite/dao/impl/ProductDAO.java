package com.ecommercewebsite.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecommercewebsite.dao.IProductDAO;
import com.ecommercewebsite.mapper.ProductMapper;
import com.ecommercewebsite.model.ProductModel;
import com.ecommercewebsite.paging.Pageble;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {

	@Override
	public Long save(ProductModel product) {
		StringBuilder sql = new StringBuilder(
				"insert into product (name,slug,price,pricesale,weight,detail,shortdesc,productimg,catid, status, available, createdAt, modifiedAt, createdBy, modifiedBy,imgPublicId) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		System.out.print(sql.toString());
		return insert(sql.toString(), product.getName(), product.getSlug(), product.getPrice(), product.getPricesale(),
				product.getWeight(), product.getDetail(), product.getShortdesc(), product.getProductimg(),
				product.getCatid(), product.getStatus(), product.getAvailable(), product.getCreatedAt(),
				product.getModifiedAt(), product.getCreatedBy(), product.getModifiedBy(),product.getImgPublicId());
	}

	@Override
	public List<ProductModel> findAll() {
		String sql = "SELECT * FROM product";
		return query(sql, new ProductMapper());
	}

	@Override
	public List<ProductModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM product where status = 1";
		return count(sql);
	}

	@Override
	public ProductModel findOne(long id) {
		String sql = "SELECT * FROM product where id = " + id;
		System.out.println(sql);
		List<ProductModel> product = query(sql, new ProductMapper());
		return product.isEmpty() ? null : product.get(0);
	}

	@Override
	public void update(ProductModel newModel) {

		StringBuilder sql = new StringBuilder("UPDATE product SET name = ?, slug = ?, price = ?,");
		sql.append(" pricesale = ?, weight = ?, detail = ?,");
		sql.append(
				" shortdesc = ?, productimg = ?, catid = ?, status = ?, available = ?, modifiedAt = ?, modifiedBy = ?, imgPublicId = ? WHERE id = ?");
		update(sql.toString(), newModel.getName(), newModel.getSlug(), newModel.getPrice(), newModel.getPricesale(),
				newModel.getWeight(), newModel.getDetail(), newModel.getShortdesc(), newModel.getProductimg(),
				newModel.getCatid(), newModel.getStatus(), newModel.getAvailable(), newModel.getModifiedAt(),
				newModel.getModifiedBy(), newModel.getImgPublicId(),newModel.getId());
	}

	@Override
	public void delete(long[] ids) {
		if (ids.length > 0) {
			StringBuilder sql = new StringBuilder("DELETE FROM product where id in (");
			for (int i = 0; i < ids.length; i++) {
				sql.append(ids[i] + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(")");
			System.out.println(sql.toString());
			update(sql.toString());
		}
	}

	@Override
	public boolean isSlugExist(String slug) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductModel findProductBySlug(String slug) {
		String sql = "SELECT * FROM product where slug = '" + slug + "'";
		List<ProductModel> list = query(sql, new ProductMapper());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<ProductModel> findByCategorySlug(Pageble page, String slug) {
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM product  LEFT JOIN category ON  product.catid = category.id ");
		sql.append("where category.slug = ?");

		System.out.println(sql.toString());
		return query(sql.toString(), new ProductMapper(), slug);
	}

	@Override
	public List<ProductModel> searchProduct(ProductModel product, Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product ");
		sql.append("WHERE name like '%" + product.getName() + "%' ");
		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortBy() + " " + pageble.getSorter().getSortName() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println("sql: " + sql.toString());
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public int getTotalItem(String fname) {
		String sql = "SELECT COUNT(*) FROM product where name like '%" + fname + "%'";
		return count(sql);
	}

	@Override
	public List<ProductModel> getListProductShow(Pageble pageble, String[] priceRanges) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product where status = 1 ");
		// Xu ly loc gia
		if (priceRanges != null && priceRanges.length > 0) {
			sql.append(" AND (");
			for (int i = 0; i < priceRanges.length; i++) {
				String[] parts = priceRanges[i].split("-");
				sql.append(" price BETWEEN " + parts[0] + " AND " + parts[1] + " ");
				if (i < priceRanges.length - 1) {
					sql.append(" OR ");
				}
			}
			sql.append(")");
		}
		System.out.println(sql.toString());

		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortBy() + " " + pageble.getSorter().getSortName() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public int getTotalItemBySlug(String slug) {
		String sql = "SELECT COUNT(*) FROM product  LEFT JOIN category ON  product.catid = category.id where category.slug = "
				+ slug;
		return count(sql);
	}

	@Override
	public List<ProductModel> getListRelatedProduct(long cateid) {
		String sql = "SELECT * FROM product where catid = " + cateid + " and status = 1";
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public List<ProductModel> findByNameLike(String name) {
		String sql = "SELECT * FROM product where name LIKE ?";
		return query(sql.toString(), new ProductMapper(), "%" + name + "%");
	}

	@Override
	public int getTotalProductCategory(String[] priceFilter) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM product where status = 1 ");
		// Xu ly loc gia
		if (priceFilter != null && priceFilter.length > 0) {
			sql.append(" AND (");
			for (int i = 0; i < priceFilter.length; i++) {
				String[] parts = priceFilter[i].split("-");
				sql.append(" price BETWEEN " + parts[0] + " AND " + parts[1] + " ");
				if (i < priceFilter.length - 1) {
					sql.append(" OR ");
				}
			}
			sql.append(")");
		}
		System.out.println("Count: " + sql.toString());
		return count(sql.toString());
	}

	@Override
	public List<ProductModel> getListProductCategoryShow(Pageble pageble, Long cateid, String[] priceRanges) {
		StringBuilder sql = new StringBuilder("SELECT * FROM product where status = 1 and catid = ").append(cateid);
		// Xu ly loc gia
		if (priceRanges != null && priceRanges.length > 0) {
			sql.append(" AND (");
			for (int i = 0; i < priceRanges.length; i++) {
				String[] parts = priceRanges[i].split("-");
				sql.append(" price BETWEEN " + parts[0] + " AND " + parts[1] + " ");
				if (i < priceRanges.length - 1) {
					sql.append(" OR ");
				}
			}
			sql.append(")");
		}
		System.out.println(sql.toString());

		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageble.getSorter().getSortBy() + " " + pageble.getSorter().getSortName() + "");
		}
		if (pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit() + "");
		}
		System.out.println(sql.toString());
		return query(sql.toString(), new ProductMapper());
	}

	@Override
	public int getTotalProductByCateId(Long cateId, String[] priceFilter) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM product where status = 1 and catid = ").append(cateId);
		// Xu ly loc gia
		if (priceFilter != null && priceFilter.length > 0) {
			sql.append(" AND (");
			for (int i = 0; i < priceFilter.length; i++) {
				String[] parts = priceFilter[i].split("-");
				sql.append(" price BETWEEN " + parts[0] + " AND " + parts[1] + " ");
				if (i < priceFilter.length - 1) {
					sql.append(" OR ");
				}
			}
			sql.append(")");
		}
		System.out.println("Count: " + sql.toString());
		return count(sql.toString());
	}

	@Override
	public List<ProductModel> getSpecialPromotion() {
		StringBuilder sql = new StringBuilder("SELECT a.* ");
		sql.append("FROM product a ");
		sql.append("JOIN product_promotion b ON a.id = b.productid ");
		sql.append("JOIN promotion p ON b.promotionid = p.id ");
		sql.append("WHERE p.hotPromotion = 1 ");
		return query(sql.toString(), new ProductMapper());
	}
	@Override
	public List<ProductModel> getSpecialPromotion(int limit) {
		StringBuilder sql = new StringBuilder("SELECT a.* ");
		sql.append("FROM product a ");
		sql.append("JOIN product_promotion b ON a.id = b.productid ");
		sql.append("JOIN promotion p ON b.promotionid = p.id ");
		sql.append("WHERE p.hotPromotion = 1 ");
		sql.append("limit " + limit);
		return query(sql.toString(), new ProductMapper());
	}
	@Override
	public List<ProductModel> getSpecialPromotion(Long promotionId) {
		StringBuilder sql = new StringBuilder("SELECT a.* ");
		sql.append("FROM product a ");
		sql.append("JOIN product_promotion b ON a.id = b.productid ");
		sql.append("WHERE b.promotionid = ? ");
		System.out.println(sql.toString());
		return query(sql.toString(), new ProductMapper(),promotionId);
	}
	public List<ProductModel> getSpecialPromotion(Long promotionId,int limit){
		StringBuilder sql = new StringBuilder("SELECT a.* ");
		sql.append("FROM product a ");
		sql.append("JOIN product_promotion b ON a.id = b.productid ");
		sql.append("WHERE b.promotionid = ? ");
		sql.append("limit " + limit);
		return query(sql.toString(), new ProductMapper(),promotionId);
	}
	@Override
	public List<ProductModel> getListNewProducts() {
		String sql = "SELECT * FROM product where status = 1 order by createdAt desc limit 7";
		return query(sql, new ProductMapper());
	}

	@Override
	public List<ProductModel> findByCategoryId(Long categoryId, int limit) {
		String sql = "SELECT * FROM product where status = 1 and catid = " + categoryId +" order by createdAt desc limit 7";
		return query(sql, new ProductMapper());
	}
}
