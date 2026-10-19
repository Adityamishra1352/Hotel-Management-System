package com.HotelManagementSystem.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(AppApplication.class, args);
		Scanner sc=new Scanner(System.in);
		Hotel hotel = context.getBean(Hotel.class);

		int choice;
		System.out.println("Welcome to Hotel Management System:");

		do{
			System.out.println("1.Login\n2.Signup");
			choice=sc.nextInt();
			switch(choice){
				case 1: System.out.println("Email address: ");
				String email=sc.next();
				System.out.println("Enter your password: ");
				String password=sc.next();
				int result=hotel.login(email, password);
				if(result==1){
					System.out.println("User Successfully Logged In!!");
					hotel.manage();
				}else{
					System.out.println("Incorrect email or password");
				}
				break;
				case 2:System.out.println("Enter your email address: ");
				String email2=sc.nextLine();
				sc.nextLine();
				System.out.println("Enter your password: ");
				String password2=sc.nextLine();

				System.out.println("Enter your name: ");
				String name=sc.nextLine();

				System.out.println("Enter your phone number: ");
				String phoneNumber=sc.nextLine();

				int result2=hotel.signup(name, phoneNumber, email2, password2);
				if(result2==1){
					System.out.println("User Successfully Signed Up!!");
				}else{
					System.out.println("Incorrect email or password");
				}
			}
		}while(choice!=3);

	}

}
