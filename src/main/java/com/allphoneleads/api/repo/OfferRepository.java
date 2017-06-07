package com.allphoneleads.api.repo;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.allphoneleads.api.domain.CampaignType;
import com.allphoneleads.api.domain.Offer;

public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {

	@Query("select o from Offer o join o.destinationNumbers od where od.phoneNumber = ?1")
	Offer findOfferByPhoneNUmber(String phoneNumber);

	@Query("select o from Offer o where o.user.id = ?1 order by o.createdAt desc")
	List<Offer> findByUser(int userId);
	
	@Query("select o from Offer o order by o.createdAt desc")
	List<Offer> getAllOrderingByCreatedAt();
	
	@Query("select count(*) from Offer c where c.campaign.id = ?1 and c.status = 'approved'")
	int findOfferCount(int campaignId);
	
	//@Query("select min(c.payout) from Offer c where c.campaign.id = ?1 and c.status = 'approved'")
	@Query(value="SELECT MIN(payout) FROM offer_destination_number od ,offers o,campaigns c WHERE c.id=?1 AND od.offer_id=o.id AND o.campaign_id=c.id and c.status = 'active' and o.status='approved'",nativeQuery =true)
	BigDecimal getMinPayout(int campaignId);
	
	//@Query("select max(c.payout) from Offer c where c.campaign.id = ?1 and c.status = 'approved'")
	@Query(value = "SELECT MAX(payout) FROM offer_destination_number od ,offers o,campaigns c WHERE c.id=?1 AND od.offer_id=o.id AND o.campaign_id=c.id and c.status = 'active' and o.status='approved'",nativeQuery =true)
	BigDecimal getMaxPayout(int campaignId);
	
	@Query("select c from Offer c where c.campaign.id = ?1 and c.status = 'approved'")
	List<Offer> findOffers(int campaignId);

	@Query("select o from Offer o where o.campaign.id = ?1 and STATUS='approved'")
	List<Offer> getCampaignOffer(int campaignId);
	
	@Query(value= "SELECT * FROM offers WHERE campaign_id=?1 AND user_id!=?2 and STATUS='approved'", nativeQuery = true)
	List<Offer> getCampaignOfferNotMediaAlpha(int campaignId, int userId);
	
	@Query(value= "SELECT * FROM offers WHERE campaign_id=?1 AND id <> ?2 AND id <> ?3 and STATUS='approved'", nativeQuery = true)
	List<Offer> getCampaignOfferNotMediaAlpha(int campaignId,  int insuredOfferid, int uninsuredOfferid);
	
	@Query(value="SELECT id  FROM offers  WHERE campaign_id = ?1 AND STATUS='approved'",nativeQuery=true)
	int[] getCommaSeperatedOffersForCampaign(int campaignId);
	
	@Query(value="SELECT id  FROM offers  WHERE campaign_id = ?1 AND STATUS='approved' AND id != ?2 ",nativeQuery=true)
	int[] getCommaSeperatedOffersForCampaign(int campaignId, int offerid);
	
	@Query("select o from Offer o where o.campaign.id = ?1")
	List<Offer> getCampaignOffer(int campaignId, String state,int offerID);

	@Query("select Distinct o.campaign.id from Offer o where o.campaign.id is not null")
	int[] getCampaignIdsWithOffers();

	@Query("select count(*) from Offer o where o.user.id = ?1 and status = ?2")
	int getOffersCountForTheAdvertiserBasedOnStatus(int advertiserId, String status);
	
	@Query("select count(id) from Offer o where o.user.id = ?1")
	int getOfferIdsCountWithAdvertiserId(int advertiserId);
	
	@Query("select o.id from Offer o where o.user.id = ?1")
	int[] getOfferIdsWithAdvertiserId(int advertiserId);

	@Query("select o from Offer o where o.id in (?1) AND STATUS='approved' AND o.id <> ?2 AND o.id <> ?3")
	List<Offer> getOffersByOfferIdsWithourMediaAlphaOffers(Integer[] offerFilterids, int insuredOfferid, int uninsuredOfferid);
	
	@Query("select o from Offer o where o.id in (?1) AND STATUS='approved' ")
	List<Offer> getOffersByOfferIds(Integer[] offerFilterids);

	@Query("select o from Offer o where o.OfferType = ?1")
	List<Offer> getOffersByType(CampaignType type);

	@Query("select o from Offer o where o.OfferType = ?1 and o.status = ?2")
	List<Offer> getOffersByTypeAndStatus(CampaignType type, String status);

	@Query("select o from Offer o where o.id != ?1")
	List<Offer> getOtherOffers(int offerId);

	@Query("select o from Offer o where o.id in ?1")
	List<Offer> getOffersByOfferIds(int[] offerIds);

	@Query("select o from Offer o where o.user.id =?1 and o.id in ?2")
	List<Offer> getUserOffersForTheIds(int userId, int[] offerIds);

	@Query("select o from Offer o where o.id =?1 and o.user.id = ?2")
	Offer findOfferByUserAndId(int offerId, int userId);
	

	@Query(value="select count(*) from offers o where o.status=?1 and o.updated_at >= ?2 and o.updated_at <= ?3 ",nativeQuery=true)
	int getCountOfCampaignsByStatusAndDateRef(String status,String rangeOne, String rangeTwo);
	
	@Query(value="select count(*) from offers o where o.status=?1 and o.user_id=?4 ",nativeQuery=true)
	int getCountOfCampaignsByStatusAndUser(String status,String rangeOne, String rangeTwo, int userID);
	
	@Query(value="select count(*) from offers o where o.status=?1  ",nativeQuery=true)
	int getCountOfCampaignsByStatusAndDateRef(String status);
	
	@Query(value="select count(*) from offers o where o.status=?1 and o.user_id=?2 ",nativeQuery=true)
	int getCountOfCampaignsByStatusAndUser(String status, int userID);
	
	@Query("select o from Offer o where o.id in (?1)")
	List<Offer> getOffersByOfferIds(Object[] offerIDs);
	
	
	@Query(value = "SELECT offers.id, offers.name, offers.type,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = offers.campaign_id AND publisher_campaigns.status = 'approved' AND publisher_campaigns.updated_at BETWEEN DATE(?1) AND DATE(?2)) AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `rpc`,"
			+ "(SELECT SUM(advertiser_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `spend`,"
			+ "offers.status "
			+ "FROM offers "
			+ "WHERE offers.user_id = ?3 "
			+ "HAVING revenue > 0 "
			+ "ORDER BY revenue DESC",  nativeQuery = true)
	List<Object[]> getOfferReportData(String from, String to, int id);
	
	@Query(value = "SELECT offers.id, offers.name, offers.type,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = offers.campaign_id AND publisher_campaigns.status = 'approved' AND publisher_campaigns.updated_at BETWEEN DATE(?1) AND DATE(?2)) AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `rpc`,"
			+ "(SELECT SUM(advertiser_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `spend`,"
			+ "offers.status "
			+ "FROM offers "
			+ "WHERE offers.user_id = ?3 "
			+ "HAVING calls > 0 "
			+ "ORDER BY calls DESC",  nativeQuery = true)
	List<Object[]> getOfferCallReportData(String from, String to, int id);
	
	@Query(value = "SELECT offers.id, offers.name, offers.type,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = offers.campaign_id AND publisher_campaigns.status = 'approved' AND publisher_campaigns.updated_at BETWEEN DATE(?1) AND DATE(?2)) AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `rpc`,"
			+ "(SELECT SUM(advertiser_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `spend`,"
			+ "offers.status "
			+ "FROM offers "
			+ "WHERE offers.user_id = ?3 "
			+ "HAVING profit > 0 "
			+ "ORDER BY profit DESC",  nativeQuery = true)
	List<Object[]> getOfferProfitReportData(String from, String to, int id);
	
	@Query(value = "SELECT offers.id, offers.name, offers.type,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = offers.campaign_id AND publisher_campaigns.status = 'approved' AND publisher_campaigns.updated_at BETWEEN DATE(?1) AND DATE(?2)) AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `rpc`,"
			+ "(SELECT SUM(advertiser_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) AND summary_hourly.advertiser_id =?3) AS `spend`,"
			+ "offers.status "
			+ "FROM offers "
			+ "WHERE offers.user_id = ?3 "
			+ "HAVING spend > 0 "
			+ "ORDER BY spend DESC",  nativeQuery = true)
	List<Object[]> getOfferSpendReportData(String from, String to, int id);
	
	@Query(value = "SELECT offers.id, offers.name, offers.type,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = offers.campaign_id AND publisher_campaigns.status = 'approved' AND publisher_campaigns.updated_at BETWEEN DATE(?1) AND DATE(?2)) AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `rpc`,"
			+ "(SELECT SUM(advertiser_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `spend`,"
			+ "offers.status "
			+ "FROM offers "
			+ "HAVING revenue > 0 "
			+ "ORDER BY revenue DESC",  nativeQuery = true)
	List<Object[]> getOfferReportDataNullId(String from, String to);
	
	@Query(value = "SELECT offers.id, offers.name, offers.type,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = offers.campaign_id AND publisher_campaigns.status = 'approved' AND publisher_campaigns.updated_at BETWEEN DATE(?1) AND DATE(?2)) AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `rpc`,"
			+ "(SELECT SUM(advertiser_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `spend`,"
			+ "offers.status "
			+ "FROM offers "
			+ "HAVING calls > 0 "
			+ "ORDER BY calls DESC",  nativeQuery = true)
	List<Object[]> getOfferCallReportDataNullId(String from, String to);
	
	@Query(value = "SELECT offers.id, offers.name, offers.type,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = offers.campaign_id AND publisher_campaigns.status = 'approved' AND publisher_campaigns.updated_at BETWEEN DATE(?1) AND DATE(?2)) AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `rpc`,"
			+ "(SELECT SUM(advertiser_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `spend`,"
			+ "offers.status "
			+ "FROM offers "
			+ "HAVING profit > 0 "
			+ "ORDER BY profit DESC",  nativeQuery = true)
	List<Object[]> getOfferProfitReportDataNullId(String from, String to);
	
	@Query(value = "SELECT offers.id, offers.name, offers.type,"
			+ "(SELECT COUNT(*) FROM publisher_campaigns WHERE publisher_campaigns.campaign_id = offers.campaign_id AND publisher_campaigns.status = 'approved' AND publisher_campaigns.updated_at BETWEEN DATE(?1) AND DATE(?2)) AS `active_publishers`,"
			+ "(SELECT SUM(total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `calls`,"
			+ "(SELECT SUM(repeat_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `repeat_calls`,"
			+ "(SELECT SUM(paid_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `paid_calls`,"
			+ "(SELECT (SUM(paid_calls) / SUM(total_calls))  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `conv`,"
			+ "(SELECT SUM(total_revenue)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `revenue`,"
			+ "(SELECT SUM(payout)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `payout`,"
			+ "(SELECT SUM(total_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `ivr_fees`,"
			+ "(SELECT SUM(total_profit)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `profit`,"
			+ "(SELECT SUM(total_revenue / total_calls)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `rpc`,"
			+ "(SELECT SUM(advertiser_cost)  FROM summary_hourly WHERE summary_hourly.offer_id = offers.id AND DATE(CONCAT(summary_hourly.year, '-', summary_hourly.month, '-', summary_hourly.day)) BETWEEN DATE(?1) AND DATE(?2) ) AS `spend`,"
			+ "offers.status "
			+ "FROM offers "
			+ "HAVING spend > 0 "
			+ "ORDER BY spend DESC",  nativeQuery = true)
	List<Object[]> getOfferSpendReportDataNullId(String from, String to);
	
	@Query("select count(o) from Offer o where o.status =?1 and date(o.updatedAt) BETWEEN DATE(?2) and DATE(?3)")
	int getOfferWithOutIdCountBetweenDates(String status,  String from, String to);
	
	@Query("select count(o) from Offer o where o.status =?1 and o.user.id = ?2 and date(o.updatedAt) BETWEEN DATE(?3) and DATE(?4)")
	int getOfferWithIdCountBetweenDates(String status, int id, String from, String to);
	
	@Query("select Distinct o.campaign.id from Offer o where o.user.id =?1")
	List<Integer> getCampaignIds( int id);
	
	@Query("select o from Offer o where o.id =?1")
	Offer getOfferById( int id);
	
	@Query("select o from Offer o where o.name =?1")
	Offer getOfferByName( String name);
	
	@Query(value = "SELECT campaign_id FROM offers o WHERE o.user_id = ?1 AND o.campaign_id <> 0 ",  nativeQuery = true)
	List<Integer> getPublisherCountByAdvertiserId(int id);

	@Query("select o from Offer o where o.id != ?1 and o.name like %?2% order by o.name asc")
	List<Offer> getFilteredOffersBasedOnParamsForAdmin(Integer current, String searchKey);

	@Query("select o from Offer o where o.id != ?1 order by o.name asc")
	List<Offer> getFilteredOffersWithoutSearchKeyForAdmin(Integer current);

	@Query("select o from Offer o order by o.name asc")
	List<Offer> getAllOffersInAscOrder();

	@Query("select o from Offer o where o.id != ?1 and o.name like %?2% and o.user.id =?3 order by o.name asc")
	List<Offer> getFilteredOffersBasedOnParamsForAdvertiser(Integer current,String searchKey, int userId);

	@Query("select o from Offer o where o.id != ?1 and o.user.id =?2 order by o.name asc")
	List<Offer> getFilteredOffersWithoutSearchKeyForAdvertiser(Integer current,int userId);

	@Query("select o from Offer o where o.user.id =?1 order by o.name asc")
	List<Offer> getAllOffersInAscOrderForAdvertiser(int userId);
	
	@Query("select count(*) from Offer c where c.campaign.id = ?1 and c.status = ?2")
    int getOfferCountBasedOnStatus(int campaignId, String status);
	
	//@Query("select min(c.payout) from Offer c where c.campaign.id = ?1 and c.status = ?2")
	@Query(value="SELECT MIN(od.payout) FROM offer_destination_number od ,offers o,campaigns c WHERE c.id=?1 and o.status = ?2 AND od.offer_id=o.id AND o.campaign_id=c.id ",nativeQuery =true)
    BigDecimal getMinPayoutForCampaign(int campaignId, String status);
	
	//@Query("select max(c.payout) from Offer c where c.campaign.id = ?1 and c.status = ?2")
	@Query(value="SELECT MAX(od.payout) FROM offer_destination_number od ,offers o,campaigns c WHERE c.id=?1 and o.status = ?2 AND od.offer_id=o.id AND o.campaign_id=c.id ",nativeQuery =true)
    BigDecimal getMaxPayoutForCampaign(int campaignId, String status);
	
	@Query("select c from Offer c where c.campaign.id = ?1 and c.status = ?2")
    List<Offer> getCampaignOffersBasedOnStatus(int campaignId, String status);
	
	@Query(value = "SELECT DISTINCT campaign_id FROM offers o WHERE o.user_id = ?1 AND o.campaign_id IS NOT NULL",  nativeQuery = true)
	List<Integer> getCampaignIdsHaveNotNull( int id);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE offers SET status = ?1, updated_at=NOW() WHERE campaign_id IN (?2)", nativeQuery=true )
	void updateCampaignStatus(String string, List<Integer> expiredCampaigns);

	@Query(value="SELECT * FROM offers WHERE expiration_type = 1 AND expiration_date <= ?1 AND STATUS != 'expired'", nativeQuery=true )
	List<Offer> getExpiredOffers(String date);
	
	@Query(value="select * from offers o ",nativeQuery=true)
	List<Offer> getOfferDetails();
	
	@Query(value="select * from offers o where o.user_id=?1 ",nativeQuery=true)
	List<Offer> getOfferDetailsWithId(int userID);
	
	@Query("select o from Offer o where o.campaign.categoryName=?1 and o.user.id !=?2 and o.status='approved'  ")
	List<Offer> getOffersByCategory(String category, int userId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE offers SET STATUS = 'suspended' WHERE NAME LIKE 'MediaAlpha-%' AND campaign_id=?1", nativeQuery=true)
	void suspendMediaAlphaOffers(int id);
	
	@Query(value="SELECT f.name,CONCAT(f.name,'--',f.id) AS decs FROM offers f WHERE f.campaign_id=?1 AND f.status='approved'",nativeQuery=true )
	List<Object[]> getOffersSpecificationData(int campaignId);
	
	//Below Queries execute in sequence to  Copy Offers 
	@Modifying
	@Transactional
	@Query(value ="INSERT INTO offers (user_id,TYPE,NAME,duration_type,call_duration,ivr_enabled,start_date,expiration_type,expiration_date,STATUS,campaign_id,call_repeat_days,transfer_prompt,audio_prompt_id,text_prompt,call_record,concurrency_type,concurrency_cap,budget_type,budget_cap,created_at,updated_at) "
			+ "SELECT user_id,TYPE,?2 AS NAME,duration_type,call_duration,ivr_enabled,start_date,expiration_type,expiration_date,'paused',campaign_id,call_repeat_days,transfer_prompt,audio_prompt_id,text_prompt,call_record,concurrency_type,concurrency_cap,budget_type,budget_cap,NOW(),NOW() "
			+ "FROM offers WHERE id=?1", nativeQuery = true)
	int copyOfferObject(int offerId, String offername);
	
	@Modifying
	@Transactional
	@Query(value ="INSERT INTO offer_destination_number (offer_id,NAME,country_code,phone_number,payout) SELECT ?1, NAME,country_code,phone_number,payout FROM offer_destination_number WHERE offer_id=?2", nativeQuery = true)
	void copyOfferDestinationObject(String newofferId, int sourceOfferId);
	
	@Modifying
	@Transactional
	@Query(value ="INSERT INTO offer_filters (offer_id, campaign_filter_id) SELECT ?1, campaign_filter_id FROM offer_filters WHERE id=?2", nativeQuery = true)
	void copyOfferFiltersObject(String newofferId, int sourceOfferId);
	
	@Modifying
	@Transactional
	@Query(value ="INSERT INTO offer_operation_times(offer_id, DAY, start_hour, end_hour, STATUS, created_at, updated_at) SELECT ?1, DAY, start_hour, end_hour, STATUS, created_at, updated_at FROM offer_operation_times WHERE offer_id=?2", nativeQuery = true)
	void copyOfferOperationsObject(String newofferId, int sourceOfferId);
	
	@Modifying
	@Transactional
	@Query(value ="INSERT INTO offer_regions(offer_id, state_code, created_at, updated_at) SELECT ?1, state_code, created_at, updated_at FROM offer_regions WHERE  offer_id=?2", nativeQuery = true)
	void copyOfferRegionsObject(String sourceOfferId, int offerId);
	
	
		

}
