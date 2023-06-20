package ru.team38.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team38.common.dto.*;
import ru.team38.userservice.services.AccountService;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/me")
    public ResponseEntity<AccountDto> getAccount() {
        return ResponseEntity.ok(accountService.getAuthenticatedAccount());
    }

    @PutMapping("/me")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody AccountDto account) {
        return ResponseEntity.ok(accountService.updateAccount(account));
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteAccount() {
        accountService.deleteAccount();
        return ResponseEntity.ok("Аккаунт удален");
    }

    @GetMapping("/search")
    public ResponseEntity<AccountResultSearchDto> findAccount(AccountSearchDto accountSearch, PageDto page) {
        return ResponseEntity.ok(accountService.findAccount(accountSearch, page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }
}
