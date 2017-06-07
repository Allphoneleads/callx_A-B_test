package com.allphoneleads.api.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.allphoneleads.api.domain.Campaign;
import com.allphoneleads.api.domain.CampaignType;

public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Integer> {

	@Query("select u from Campaign u where u.name = ?1")
	Campaign findCampaignByName(String name);

	@Query("select u from Campaign u where u.id = ?1")
	Campaign getId(int campaignId);
		
	@Query("update Campaign u set u.status = 'suspended' where u.id = ?1 ")
	Campaign ChangeStatus(int campaignId);

	@Query("select c from Campaign c where c.OfferType=?1")
	List<Campaign> getAllBundledCampaigns(CampaignType bundled);

	@Query("select c from Campaign c where c.OfferType=?1 and c.status =?2")
	List<Campaign> getCampaignsBasedOnStatus(CampaignType bundled,String status);
	
	@Query("select c from Campaign c where c.OfferType=?1 and c.status =?2 and c.enableABtest=?3")
	List<Campaign> getCampaignsForABtest(CampaignType bundled,String status, boolean value);
	
	@Query("select c from Campaign c where c.OfferType=?1 and c.status =?2 ")
	List<Campaign> getCampaignsForABtestForAllActive(CampaignType bundled,String status);
	
	@Query(value="select count(*) from campaigns c where c.status=?1 and updated_at >= ?2 and updated_at <= ?3 " , nativeQuery=true)
	int getCountOfCampaignsByStatus(String status, String rangeOne,String rangeTwo);
	
	@Query(value="SELECT COUNT(*) FROM campaigns c ,publisher_campaigns pc WHERE pc.campaign_id=c.id AND pc.status=?1 AND pc.user_id=?2 AND pc.is_admin=0 AND pc.updated_at >= ?3 AND pc.updated_at <= ?4 " , nativeQuery=true)
	int getCountOfCampaignsByStatusAndUserId(String status, int userid, String rangeOne,String rangeTwo);
	
	@Query(value="select count(*) from campaigns c where c.status=?1 AND CURDATE() >= c.start_date  " , nativeQuery=true)
	int getCountOfCampaignsByStatus(String status);
	
	@Query(value="SELECT COUNT(*) FROM campaigns c ,publisher_campaigns pc WHERE pc.campaign_id=c.id AND pc.status=?1 AND pc.user_id=?2 AND pc.is_admin=0 AND CURDATE() >= c.start_date " , nativeQuery=true)
	int getCountOfCampaignsByStatusAndUserId(String status, int userid);
	
	@Query(value="SELECT COUNT(*) FROM campaigns c ,publisher_campaigns pc WHERE pc.campaign_id=c.id AND pc.status=?1 AND pc.user_id=?2 AND pc.is_admin=0 " , nativeQuery=true)
	int getCountOfCampaignsByPendingAndUserId(String status, int userid);
	
	@Query(value="select count(*) from campaigns c where CURDATE() < c.start_date  " , nativeQuery=true)
	int getCountOfCampaignsByPending();
	
	@Query("select c from Campaign c where id in ?1 ")
	List<Campaign> getCampaignsByCampaignIds(Object[] campaignIDs);
	
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3 AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING revenue > 0 "
			+ "ORDER BY revenue DESC",  nativeQuery = true)
	List<Object[]> getCampaignReportData(String from, String to, int id);
	
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3 AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3 )  AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3 )  AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3 )  AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3 ) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3)  AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3 )  AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING revenue > 0 "
			+ "ORDER BY revenue DESC",  nativeQuery = true)
	List<Object[]> getCampaignPublisherReportData(String from, String to, int publisherId);
	
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3 AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING calls > 0 "
			+ "ORDER BY calls DESC",  nativeQuery = true)
	List<Object[]> getCampaignPublisherCallReportData(String from, String to, int publisherId);
	
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3 AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.publisher_id=?3) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING profit > 0 "
			+ "ORDER BY profit DESC",  nativeQuery = true)
	List<Object[]> getCampaignPublisherProfitReportData(String from, String to, int publisherId);
	
	//
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3 AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING calls > 0 "
			+ "ORDER BY calls DESC",  nativeQuery = true)
	List<Object[]> getCampaignCallReportData(String from, String to, int id);
	
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) AND publisher_campaigns.user_id = ?3 AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING profit > 0 "
			+ "ORDER BY profit DESC",  nativeQuery = true)
	List<Object[]> getCampaignProfitReportData(String from, String to, int id);
	
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) ) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2)  AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING revenue > 0 "
			+ "ORDER BY revenue DESC",  nativeQuery = true)
	List<Object[]> getCampaignReportDataNullId(String from, String to);
	
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) ) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2)  AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING calls > 0 "
			+ "ORDER BY calls DESC",  nativeQuery = true)
	List<Object[]> getCampaignCallReportDataNullId(String from, String to);
	
	@Query(value ="SELECT campaigns.id, campaigns.name, campaigns.type, campaigns.category_name,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2) ) AS 'publishers',"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = campaigns.id AND publisher_campaigns.updated_at BETWEEN DATE(?1 ) AND DATE(?2)  AND publisher_campaigns.status = 'approved') AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.campaign_id = campaigns.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2)) AS `rpc`,"
			+ " campaigns.status "
			+ "FROM campaigns "
			+ " HAVING profit > 0 "
			+ "ORDER BY profit DESC",  nativeQuery = true)
	List<Object[]> getCampaignProfitReportDataNullId(String from, String to);
	
	@Query("select count(c) from Campaign c where c.status =?1 and date(c.updatedAt) BETWEEN date(?2) and date(?3)")
	int getCampaignCountBetweenDates(String status, String from, String to);

	@Query("select c from Campaign c where c.OfferType=?1 and c.categoryName =?2")
	List<Campaign> getBundledCampaignsByCategory(CampaignType bundled, String categoryName);

	@Query("select c from Campaign c where c.id in ?1")
	List<Campaign> getCampaignsByIds(int[] campaignIds);

	@Query("select c from Campaign c where c.id != ?1 and c.name like %?2% order by c.name asc")
	List<Campaign> getFilteredCampaignsBasedOnParamsForAdmin(Integer current, String searchKey);

	@Query("select c from Campaign c where c.id != ?1 order by c.name asc")
	List<Campaign> getFilteredCampaignsWithoutSearchKeyForAdmin(Integer current);

	@Query("select c from Campaign c order by c.name asc")
	List<Campaign> getAllCampaignsInAscOrder();
	
	@Query("select c from Campaign c order by c.createdAt desc")
    List<Campaign> getAllCampaignsInDescOrderOfCreatedDate();
	
	@Query(value="SELECT * FROM campaigns WHERE expiration_type = 1 AND expiration_date <= date(?1) AND status != 'expired'", nativeQuery=true )
	List<Campaign> getExpiredCampaigns(String date);
	
	@Query("SELECT c FROM Campaign c WHERE c.expirationType = true AND c.expirationDate <= NOW() AND c.status != 'expired'" )
	List<Campaign> getExpiredCampaigns();
	
	@Modifying
	@Transactional
	@Query(value="UPDATE campaigns SET status = ?1, updated_at=NOW() WHERE id IN (?2)", nativeQuery=true )
	void updateCampaignStatus(String status, List<Integer> expiredCampaigns);
	
	@Query(value="select * from campaigns c  " , nativeQuery=true)
	List<Campaign> getCampaignDetails();
	
	@Query(value="SELECT * FROM campaigns c ,publisher_campaigns pc WHERE pc.campaign_id=c.id and pc.user_id=?1 AND pc.is_admin=0 " , nativeQuery=true)
	List<Campaign> getCampaignDetailsUserId(int userId);
	
	
	@Query(value="SELECT c.id, c.name AS campaignname, CONCAT(c.name,'--',c.id) AS des,f.name AS offername, SUM(sh.total_calls), SUM(total_revenue),ROUND(SUM(total_revenue)/SUM(sh.total_calls),1) FROM campaigns c JOIN offers f ON (f.campaign_id=c.id) JOIN summary_hourly sh ON (sh.offer_id=f.id)   WHERE c.type='bundled' AND c.status='active' AND f.status='approved' AND c.enable_abtest=TRUE GROUP BY c.name,f.name " , nativeQuery=true) 
	List<Object[]> getCampaignsSpecificationData();
	
	@Query(value="SELECT f.id,f.name AS offername, CASE WHEN SUM(sh.total_calls) IS NULL THEN 0 ELSE SUM(sh.total_calls) END, CASE WHEN SUM(total_revenue) IS NULL THEN 0 ELSE SUM(total_revenue) END , CASE WHEN ROUND(SUM(total_revenue)/SUM(sh.total_calls),1) IS NULL THEN 0 ELSE ROUND(SUM(total_revenue)/SUM(sh.total_calls),1) END FROM  offers f LEFT JOIN summary_hourly sh ON (sh.offer_id=f.id)   WHERE f.campaign_id=?1 AND f.status='approved' GROUP BY f.id " , nativeQuery=true) 
	List<Object[]> getOfferSpecificationData(int campaignID);
	
	
	@Modifying
	@Transactional
	@Query(value ="INSERT INTO campaigns (TYPE,NAME,description,start_date,expiration_type,expiration_date,payout_percentage,STATUS,category_name,enable_abtest,visible_to,copycount,out_bound,lead_field_type,created_at,updated_at,is_repeat,is_record,abtest_percentage) " 
			+ " SELECT TYPE,?2,description,NOW(),expiration_type,expiration_date,payout_percentage,?4,?3,enable_abtest,visible_to,copycount,out_bound,lead_field_type,NOW(),NOW(),is_repeat,is_record,abtest_percentage "
			+ "FROM campaigns WHERE id=?1", nativeQuery = true)
	int copyCampaignObject(int campaignId, String campaignName, String categoryName, String status);
	
	@Modifying
	@Transactional
	@Query(value =	"INSERT INTO campaign_filters (campaign_id,NAME) SELECT ?2,NAME FROM campaign_filters WHERE campaign_id=?1", nativeQuery = true)
	void copyCampaignFilterObject(int oldCampaignId, int newCampaignId);
	
	@Query("select c from Campaign c where c.categoryName = ?1 and c.name=?2")
	Campaign getCampaignWithCategoryAndName(String category, String campaignName);
	
	@Modifying
	@Transactional
	@Query(value = "delete from campaign_filters where campaign_id=?1", nativeQuery =true)
	void removingFilters(int campaignId);
	
	@Modifying
	@Transactional
	@Query(value = "delete from campaigns where id=?1", nativeQuery =true)
	void removingCampaign(int campaignId);
	
	@Query(value = "SELECT DISTINCT(c.id),c.name FROM campaigns c, publisher_campaigns p WHERE c.status= 'active' AND c.id=p.campaign_id AND p.status='approved' AND p.is_admin=0", nativeQuery = true)
	List<Object[]> getActiveCampaignNames();
	
	
}
