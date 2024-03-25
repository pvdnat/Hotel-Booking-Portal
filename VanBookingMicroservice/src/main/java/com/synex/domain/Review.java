package com.synex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="review")
public class Review {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int reviewId;
		
		private String text;
		private double overallRating;

		public Review() {}

		public int getReviewId() {
			return reviewId;
		}

		public void setReviewId(int reviewId) {
			this.reviewId = reviewId;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public double getOverallRating() {
			return overallRating;
		}

		public void setOverallRating(double overallRating) {
			this.overallRating = overallRating;
		}
		
		
}
