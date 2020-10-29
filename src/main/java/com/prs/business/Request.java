package com.prs.business;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
		private int id;
		@ManyToOne
		@JoinColumn(name="UserID")		
		private User user;
		private String description;
		private String justification;
		private String dateNeeded;
		private String deliveryMode;
		private String status;
		private double total;
		private LocalDate submittedDate;
		private String reasonForRejection;
		
		public Request() {
			super();
		}

		public Request(int id, User user, String description, String justification, String dateNeeded,
				String deliveryMode, String status, double total, LocalDate submittedDate, String reasonForRejection) {
			super();
			this.id = id;
			this.user = user;
			this.description = description;
			this.justification = justification;
			this.dateNeeded = dateNeeded;
			this.deliveryMode = deliveryMode;
			this.status = status;
			this.total = total;
			this.submittedDate = submittedDate;
			this.reasonForRejection = reasonForRejection;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getJustification() {
			return justification;
		}

		public void setJustification(String justification) {
			this.justification = justification;
		}

		public String getDateNeeded() {
			return dateNeeded;
		}

		public void setDateNeeded(String dateNeeded) {
			this.dateNeeded = dateNeeded;
		}

		public String getDeliveryMode() {
			return deliveryMode;
		}

		public void setDeliveryMode(String deliveryMode) {
			this.deliveryMode = deliveryMode;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public double getTotal() {
			
		
			
			return total;
		}

		public double setTotal(Double total) {
			this.total = total;
			return total;
		}

		public LocalDate getSubmittedDate() {
			return submittedDate;
		}

		public void setSubmittedDate(LocalDate localDate) {
			this.submittedDate = localDate;
		}

		public String getReasonForRejection() {
			return reasonForRejection;
		}

		public void setReasonForRejection(String reasonForRejection) {
			this.reasonForRejection = reasonForRejection;
		}

		
		
}
