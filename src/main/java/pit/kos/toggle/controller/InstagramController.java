package pit.kos.toggle.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.brunocvcunha.instagram4j.requests.InstagramFollowRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramLikeRequest;
import org.brunocvcunha.instagram4j.requests.InstagramPostCommentRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUnfollowRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;
import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pit.kos.toggle.aspect.FeatureAssociation;
import pit.kos.toggle.constant.Endpoints;
import pit.kos.toggle.entity.Followers;
import pit.kos.toggle.entity.Media;
import pit.kos.toggle.entity.SearchData;
import pit.kos.toggle.entity.User;
import pit.kos.toggle.feature.MyFeatures;
import pit.kos.toggle.services.LoginRepository;
import pit.kos.toggle.utils.Validator;

/**
 * @author Piotr Kosmala
 * 
 */

@RestController
@RequestMapping(Endpoints.INSTAGRAM_API)
public class InstagramController {

	private static final Logger logger = Logger.getLogger(InstagramController.class);

	@Autowired
	private LoginRepository loginRepository;

	@FeatureAssociation(value = MyFeatures.SEARCH_USER_FEATURE)
	@RequestMapping(path = Endpoints.INSTAGRAM_API_SEARCH_USER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InstagramUser> searchUser(@RequestBody SearchData searchData) {
		try {
			logger.info("search user:" + searchData.getUser());
			if (Validator.validateSearchData(searchData)) {
				InstagramSearchUsernameResult searchResult = loginRepository.login(searchData.getLoginData()).getInstagram4j().sendRequest(new InstagramSearchUsernameRequest(searchData.getUser()));
				return new ResponseEntity<>(searchResult.getUser(), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return new ResponseEntity<>(new InstagramUser(), HttpStatus.BAD_REQUEST);
	}
	
	@FeatureAssociation(value = MyFeatures.GET_USER_FOLLOWERS)
	@RequestMapping(path = Endpoints.INSTAGRAM_API_GET_USER_FOLLOWERS, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InstagramUserSummary>> getUserFollowers(@RequestBody Followers followers) {
		try {
			logger.info("getUserFollowers:" + followers.getPk());
			if (Validator.validateFollowers(followers)) {
				User user = loginRepository.login(followers.getLoginData());
				InstagramGetUserFollowersResult followersUser = user.getInstagram4j().sendRequest(new InstagramGetUserFollowersRequest(followers.getPk()));
				return new ResponseEntity<>(followersUser.getUsers(), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return new ResponseEntity<>(new ArrayList<InstagramUserSummary>(0), HttpStatus.BAD_REQUEST);
	}
	
	@FeatureAssociation(value = MyFeatures.USER_FOLLOW)
	@RequestMapping(path = Endpoints.INSTAGRAM_API_USER_FOLLOW, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusResult> userFollow(@RequestBody Followers followers) {
		try {
			logger.info("userFollow:" + followers.getPk());
			if (Validator.validateFollowers(followers)) {
				User user = loginRepository.login(followers.getLoginData());
				StatusResult result = user.getInstagram4j().sendRequest(new InstagramFollowRequest(followers.getPk()));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return new ResponseEntity<>(new StatusResult(), HttpStatus.BAD_REQUEST);
	}
	
	@FeatureAssociation(value = MyFeatures.USER_UNFOLLOW)
	@RequestMapping(path = Endpoints.INSTAGRAM_API_USER_UNFOLLOW, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusResult> userUnfollow(@RequestBody Followers followers) {
		try {
			logger.info("userUnfollow:" + followers.getPk());
			if (Validator.validateFollowers(followers)) {
				User user = loginRepository.login(followers.getLoginData());
				StatusResult result = user.getInstagram4j().sendRequest(new InstagramUnfollowRequest(followers.getPk()));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return new ResponseEntity<>(new StatusResult(), HttpStatus.BAD_REQUEST);
	}
	
	@FeatureAssociation(value = MyFeatures.SEARCH_USER_FEATURE)
	@RequestMapping(path = Endpoints.INSTAGRAM_API_TAG_FEED, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InstagramFeedResult> getHashTag(@RequestBody SearchData searchData) {
		try {
			logger.info("getHashTag:" + searchData.getTag());
			if (Validator.validateSearchData(searchData)) {
				InstagramFeedResult tagFeed = loginRepository.login(searchData.getLoginData()).getInstagram4j().sendRequest(new InstagramTagFeedRequest(searchData.getTag()));
				return new ResponseEntity<>(tagFeed, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return new ResponseEntity<>(new InstagramFeedResult(), HttpStatus.BAD_REQUEST);
	}
	
	@FeatureAssociation(value = MyFeatures.LIKE)
	@RequestMapping(path = Endpoints.INSTAGRAM_API_LIKE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusResult> like(@RequestBody Media media) {
		try {
			logger.info("like:" + media.getPk());
			if (Validator.validateMediaLike(media)) {
				StatusResult result = loginRepository.login(media.getLoginData()).getInstagram4j().sendRequest(new InstagramLikeRequest(media.getPk()));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return new ResponseEntity<>(new StatusResult(), HttpStatus.BAD_REQUEST);
	}
	
	@FeatureAssociation(value = MyFeatures.COMMENT)
	@RequestMapping(path = Endpoints.INSTAGRAM_API_COMMENT, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusResult> comment(@RequestBody Media media) {
		try {
			logger.info("comment:" + media.getPk());
			if (Validator.validateMediaComment(media)) {
				StatusResult result = loginRepository.login(media.getLoginData()).getInstagram4j().sendRequest(new InstagramPostCommentRequest(media.getPk(), media.getComment()));
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return new ResponseEntity<>(new StatusResult(), HttpStatus.BAD_REQUEST);
	}
}
