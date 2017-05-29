package com.fastaccess.data.service;

import android.support.annotation.NonNull;

import com.fastaccess.data.dao.Pageable;
import com.fastaccess.data.dao.model.Event;
import com.fastaccess.data.dao.model.Login;
import com.fastaccess.data.dao.model.Repo;
import com.fastaccess.data.dao.model.User;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


/**
 * Created by Kosh on 08 Feb 2017, 8:54 PM
 */

public interface UserRestService {

    @GET("user") Observable<Login> getUser();

    @GET("users/{username}") Observable<User> getUser(@Path("username") @NonNull String username);

    @GET("users/{username}/received_events")
    Observable<Pageable<Event>> getReceivedEvents(@NonNull @Path("username") String userName, @Query("page") int page);

    @GET("users/{username}/repos?affiliation=owner,collaborator&sort=pushed&direction=desc")
    Observable<Pageable<Repo>> getRepos(@Path("username") @NonNull String username, @Query("page") int page);

    @GET("/user/repos?affiliation=owner,collaborator&sort=pushed&direction=desc")
    Observable<Pageable<Repo>> getRepos(@Query("page") int page);

    @GET("users/{username}/starred") Observable<Pageable<Repo>>
    getStarred(@Path("username") @NonNull String username, @Query("page") int page);

    @GET("users/{username}/following")
    Observable<Pageable<User>> getFollowing(@Path("username") @NonNull String username, @Query("page") int page);

    @GET("users/{username}/followers")
    Observable<Pageable<User>> getFollowers(@Path("username") @NonNull String username, @Query("page") int page);

    @GET("user/following/{username}")
    Observable<Response<Boolean>> getFollowStatus(@Path("username") @NonNull String username);

    @PUT("/user/following/{username}")
    Observable<Response<Boolean>> followUser(@Path("username") @NonNull String username);

    @DELETE("/user/following/{username}")
    Observable<Response<Boolean>> unfollowUser(@Path("username") @NonNull String username);

    @GET Observable<String> getContributions(@Url String url);
}
