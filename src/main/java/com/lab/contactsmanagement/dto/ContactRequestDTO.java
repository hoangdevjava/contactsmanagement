package com.lab.contactsmanagement.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContactRequestDTO {

	@NotBlank(message = "Invalid Name: Empty name")
	@NotNull(message = "Invalid Name: Name is NULL")
	@Size(min = 3, max = 30, message = "Invalid Name: Exceeds 30 characters")
	private String name;

	@Email(message = "Invalid email")
	private String email;

	@NotBlank(message = "Invalid Phone number: empty number")
	@NotNull(message = "Invalid Phone number: Number is NULL")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
	private String telephone;

	@NotBlank(message = "Invalid Postal Address: empty number")
	@NotNull(message = "Invalid Postal Address: Postal Address is NULL")
	private String postalAddress;
}
