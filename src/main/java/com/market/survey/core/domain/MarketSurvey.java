package com.market.survey.core.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "market_survey")
public class MarketSurvey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long identification;
	private String description;
	@Column(name = "work_init")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fieldworkTimeInit;
	@Column(name = "work_end")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fieldworkTimeEnd;
	@Column(name = "target_group_description")
	private String targetGroupDescription;
	@Column(name = "sample_size")
	private int sampleSize;
	private Channel channel;
	private String organisation;
	@Column(name = "registration_type")
	private RegistrationType registrationType;
	private Method method;
	private Type type;
	@Column(name = "time_series")
	private boolean timeSeries;
	@ManyToOne
	private Party provider;
	@OneToMany(cascade = CascadeType.MERGE)
	private Set<Party> requesters;
	@OneToMany
	private Set<Country> countries;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Condition> conditions;

	/* for auditiong entity */
	@JsonIgnore
	@Column(name = "created", insertable = true, updatable = false)
	private Timestamp createdAt;
	@JsonIgnore
	@Column(name = "updated", insertable = false, updatable = true)
	private Timestamp updatedAt;

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	void onCreate() {
		this.setCreatedAt((new Timestamp((new Date()).getTime())));
	}

	@PreUpdate
	void onPersist() {
		this.setUpdatedAt(new Timestamp((new Date()).getTime()));

	}

	public Party getProvider() {
		return provider;
	}

	public void setProvider(Party provider) {
		this.provider = provider;
	}

	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}

	public Set<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}

	public Set<Party> getRequesters() {
		return requesters;
	}

	public void setRequesters(Set<Party> requesters) {
		this.requesters = requesters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFieldworkTimeInit() {
		return fieldworkTimeInit;
	}

	public void setFieldworkTimeInit(Date fieldworkTimeInit) {
		this.fieldworkTimeInit = fieldworkTimeInit;
	}

	public Date getFieldworkTimeEnd() {
		return fieldworkTimeEnd;
	}

	public void setFieldworkTimeEnd(Date fieldworkTimeEnd) {
		this.fieldworkTimeEnd = fieldworkTimeEnd;
	}

	public String getTargetGroupDescription() {
		return targetGroupDescription;
	}

	public void setTargetGroupDescription(String targetGroupDescription) {
		this.targetGroupDescription = targetGroupDescription;
	}

	public int getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public RegistrationType getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(RegistrationType registrationType) {
		this.registrationType = registrationType;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isTimeSeries() {
		return timeSeries;
	}

	public void setTimeSeries(boolean timeSeries) {
		this.timeSeries = timeSeries;
	}

	public long getIdentification() {
		return identification;
	}

	public void setIdentification(long identification) {
		this.identification = identification;
	}

}
