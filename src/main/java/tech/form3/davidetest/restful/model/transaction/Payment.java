package tech.form3.davidetest.restful.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Payment implements Serializable {
	@Id
	@Column(name = "id")
	@JsonProperty("id")
	private String id = UUID.randomUUID().toString(); // Generate a random string

	@Column(name = "type")
	@JsonProperty("type")
	private String type;

	@Version
	@Column(name = "version")
	@JsonProperty("version")
	private Integer version;

	@Column(name = "organization_id")
	@JsonProperty("organization_id")
	private String organizationId;

	@Valid
	@Transient
	@JsonProperty("attributes")
	private transient Attributes attributes;

	public Payment(Payment payment) {
		this.id = payment.getId();
		this.type = payment.getType();
		this.version = payment.getVersion();
		this.organizationId = payment.getOrganizationId();
		this.attributes = payment.getAttributes();
	}
}
