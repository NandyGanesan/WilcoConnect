package com.android.wilcoconnect.api;

import com.android.wilcoconnect.model.wilcoconnect.IssueTracking;
import com.android.wilcoconnect.model.login.Login;
import com.android.wilcoconnect.model.wilcoconnect.MyRequestData;
import com.android.wilcoconnect.model.wilcoconnect.RequestType;
import com.android.wilcoconnect.model.common.Success;
import com.android.wilcoconnect.model.login.TokenData;
import com.android.wilcoconnect.model.common.UserData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL = "http://192.168.1.50/hrdev/";

    /*
    * Get the Token to get the session
    * */
    @FormUrlEncoded
    @POST("token")
    Call<TokenData> getToken(@Field("username") String username,
                             @Field("Password") String password,
                             @Field("grant_type") String grant_type);

    /*
    * Login Operation
    * */
    @POST("api/Login/Post")
    Call<UserData> login(@Body Login data);

    /*
    * Get the overall service request based
    * Support Function
    * Request Type
    * Priority
    * Dropdown data
    * */
    @GET("api/ServiceRequest/newServiceRequest?")
    Call<RequestType> getDropdownData(@Query("Email") String Email,
                                      @Query("companyCode") String companyCode,
                                      @Query("employeeID") String employeeID);
    /*
    * Store the particular service request to database
    * */
    @Multipart
    @POST("api/ServiceRequest/StoreRequests")
    Call<Success> StoreRequest(@Part MultipartBody.Part file,
                               @Part("Email") RequestBody Email,
                               @Part("EmployeeID") RequestBody employeeID,
                               @Part("companyCode") RequestBody companyCode,
                               @Part("IssueMasterID") RequestBody IssueMasterID,
                               @Part("supportFunctionID") RequestBody supportFunctionID,
                               @Part("requestTypeId") RequestBody requestTypeId,
                               @Part("priorityLevelID") RequestBody priorityLevelID,
                               @Part("issueSummary") RequestBody issueSummary,
                               @Part("issueDescription") RequestBody issueDescription,
                               @Part("statuscode") RequestBody statuscode,
                               @Part("Comments") RequestBody Comments);

    /*
    * Get the overall request detail for the particular user
    * */
    @GET("api/ServiceRequest/myRequestList")
    Call<MyRequestData> MyRequest(@Query("Email") String Email,
                                  @Query("companyCode") String companyCode,
                                  @Query("employeeID") String employeeID,
                                  @Query("statusLink") String statusLink);

    /*
    * Get the overall operation information
    * */
    @GET("api/ServiceRequest/getServiceRequestDetails")
    Call<IssueTracking> IssueTracking(@Query("Email") String Email,
                                      @Query("companyCode") String companyCode,
                                      @Query("employeeID") String employeeID,
                                      @Query("statusCode") String statusCode,
                                      @Query("masterID") int masterID);
}