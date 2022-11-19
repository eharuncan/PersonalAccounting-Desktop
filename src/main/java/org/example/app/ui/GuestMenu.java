package org.example.app.ui;

import org.example.app.domain.User;

import java.util.Objects;

import static org.example.app.App.*;

public class GuestMenu {
    private final Common common;

    public GuestMenu(Common common) {
        this.common = common;
    }

    public void show() {
        System.out.println("\nHoşgeldiniz...");

        loops:
        while (true) {
            common.menuHeader();
            System.out.println("1- Giriş Yap");
            System.out.println("2- Kaydol");
            common.menuFooter();

            String input = common.getInput(null);
            if (Objects.equals(input, "1")) {
                String email;
                String password;
                while (true) {
                    System.out.println("\nEposta adresinizi giriniz:");
                    email = common.getInput(null);

                    System.out.println("\nŞifrenizi giriniz:");
                    password = common.getInput(null);

                    User newUser = new User("","", email, password);

                    currentUser = userService.login(newUser);
                    System.out.println("\nBaşarıyla kullanıcı girişi yapıldı.");
                    common.menuSelector();
                    break loops;
                }
            } else if (Objects.equals(input, "2")) {
                while (true) {
                    System.out.println("\nAdınızı giriniz:");
                    String name = (common.getInput(null));

                    System.out.println("\nSoyadınızı giriniz:");
                    String surname = (common.getInput(null));

                    System.out.println("\nEposta adresinizi giriniz:");
                    String email = (common.getInput(null));

                    String password = (common.getInput(null));

                    String retypedPassword = (common.getInput(null));

                    User newUser = new User(name, surname, email, password );

                    if (common.checkPasswords(password, retypedPassword)) {
                        User registeredUser = userService.register(newUser);
                        if (registeredUser != null) {
                            currentUser = registeredUser;
                            System.out.println("\nKullanıcı kaydı başarıyla gerçekleşti.");
                            common.menuSelector();
                            break loops;
                        } else {
                            System.out.println("\nHata: kullanıcı kaydı oluşturulamadı.");
                        }
                    }
                }
            } else if (Objects.equals(input, "ç")) {
                break;
            } else {
                System.out.println("\nHata: Lütfen doğru seçeneği giriniz.");
            }
        }
    }
}
