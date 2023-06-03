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

	@NotBlank(message = "{name.notempty}")
	@NotNull(message = "{name.notnull}")
	@Size(min = 3, max = 30, message = "{name.invalidlength}")
	private String name;

	@Email(message = "{email.invalid}")
	private String email;

	@NotBlank(message = "{telephone.notempty}")
	@NotNull(message = "{telephone.notnull}")
    @Pattern(regexp = "^\\d{10}$", message = "{telephone.invalid}")
	private String telephone;

	@NotBlank(message = "{telephone.notempty}")
	@NotNull(message = "{telephone.notnull}")
	private String postalAddress;
}
