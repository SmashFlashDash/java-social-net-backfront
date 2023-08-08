package ru.team38.gatewayservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team38.common.dto.*;
import ru.team38.common.dto.notification.DataTimestampDto;
import ru.team38.common.dto.notification.NotificationSettingDto;
import ru.team38.common.dto.notification.NotificationUpdateDto;
import ru.team38.common.dto.notification.NotificationsPageDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-service", url = "${spring.services.user.url}")
public interface UserServiceClient {

    @PostMapping("/api/v1/auth/register")
    ResponseEntity<String> register(@RequestBody RegisterDto registerDto);

    @PostMapping("/api/v1/auth/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginForm loginForm);

    @PostMapping("/api/v1/auth/logout")
    ResponseEntity<String> logout();

    @PostMapping("/api/v1/auth/refresh")
    ResponseEntity<LoginResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest);

    @GetMapping("/api/v1/auth/captcha")
    ResponseEntity<CaptchaDto> getCaptcha();

    @GetMapping("/api/v1/friends/count")
    ResponseEntity<CountDto> getIncomingFriendRequestsCount();

    @PostMapping("/api/v1/account")
    ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto);

    @GetMapping("/api/v1/account/me")
    ResponseEntity<AccountDto> getAccount();

    @PutMapping("/api/v1/account/me")
    ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto account);

    @DeleteMapping("/api/v1/account/me")
    ResponseEntity<String> deleteAccount();


    @GetMapping("/api/v1/account/{id}")
    ResponseEntity<AccountDto> getAccountById(@PathVariable UUID id);

    @GetMapping("/api/v1/notifications/count")
    ResponseEntity<DataTimestampDto> getNotificationsCount();

    @GetMapping("/api/v1/notifications")
    ResponseEntity<NotificationsPageDto> getNotificationsPage();

    @PutMapping("/api/v1/notifications/readed")
    ResponseEntity<String> readAllNotifications();

    @GetMapping("/api/v1/notifications/settings")
    ResponseEntity<NotificationSettingDto> getNotificationSetting();

    @PutMapping("/api/v1/notifications/settings")
    ResponseEntity<NotificationSettingDto> updateNotificationSetting(@RequestBody NotificationUpdateDto notificationUpdateDto);

    @PostMapping("/api/v1/notifications/settings/{id}")
    ResponseEntity<NotificationSettingDto> setNotificationSetting(@PathVariable UUID id);

    @GetMapping("/api/v1/friends")
    ResponseEntity<PageFriendShortDto> getFriendsByParameters(
            @RequestParam("statusCode") StatusCode statusCode,
            @RequestParam("firstName") String firstName,
            @RequestParam("city") String city,
            @RequestParam("country") String country,
            @RequestParam("ageFrom") Integer ageFrom,
            @RequestParam("ageTo") Integer ageTo,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("sort") List<String> sort
    );

    @GetMapping("/api/v1/friends/recommendations")
    ResponseEntity<List<FriendShortDto>> getFriendsRecommendations(
            @RequestParam("firstName") String firstName,
            @RequestParam("city") String city,
            @RequestParam("country") String country,
            @RequestParam("ageFrom") Integer ageFrom,
            @RequestParam("ageTo") Integer ageTo
    );

    @GetMapping("/api/v1/geo/country")
    ResponseEntity<List<CountryDto>> getCountries();

    @GetMapping("/api/v1/geo/country/{countryId}/city")
    ResponseEntity<List<CityDto>> getCitiesByCountryId(@PathVariable("countryId") String countryId);

    @GetMapping("/api/v1/account/search")
    ResponseEntity<PageAccountDto> findAccount(@RequestParam String firstName,
                                               @RequestParam String lastName,
                                               @RequestParam Integer ageFrom,
                                               @RequestParam Integer ageTo,
                                               @RequestParam String author,
                                               @RequestParam List<String> ids,
                                               @RequestParam Boolean isDeleted);

    @PutMapping("/api/v1/friends/block/{id}")
    ResponseEntity<FriendShortDto> blockAccount(@PathVariable UUID id);

    @PutMapping("/api/v1/friends/unblock/{id}")
    ResponseEntity<FriendShortDto> unblockAccount(@PathVariable UUID id);

    @PostMapping("/api/v1/auth/password/recovery")
    ResponseEntity<String> recoverPassword(@RequestBody PasswordRecoveryDto passwordRecoveryDto);

    @PostMapping("/api/v1/auth/password/recovery/{linkId}")
    ResponseEntity<String> setNewPassword(@PathVariable String linkId,
                                               @RequestBody NewPasswordDto newPasswordDto);

    @PostMapping("/api/v1/friends/{id}/request")
    ResponseEntity<FriendShortDto> makeFriendRequest(@PathVariable UUID id);

    @PutMapping("/api/v1/friends/{id}/approve")
    ResponseEntity<FriendShortDto> approveFriendRequest(@PathVariable UUID id);

    @DeleteMapping("/api/v1/friends/{id}")
    void deleteRelationship(@PathVariable UUID id);

    @PostMapping("/api/v1/friends/subscribe/{id}")
    ResponseEntity<FriendShortDto> getSubscription(@PathVariable UUID id);
}