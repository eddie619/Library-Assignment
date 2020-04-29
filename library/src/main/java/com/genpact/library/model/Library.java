package com.genpact.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARY")
public class Library {

	public Library() {
	}

	public Library(String libraryName) {
		this.libraryName = libraryName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "libraryid")
	private long libraryId;

	@Column(name = "libraryname")
	private String libraryName;

	public long getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(long libraryId) {
		this.libraryId = libraryId;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (libraryId ^ (libraryId >>> 32));
		result = prime * result + ((libraryName == null) ? 0 : libraryName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Library other = (Library) obj;
		if (libraryId != other.libraryId) {
			return false;
		}
		if (libraryName == null) {
			if (other.libraryName != null) {
				return false;
			}
		} else if (!libraryName.equals(other.libraryName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Library [libraryId=").append(libraryId).append(", libraryName=").append(libraryName)
				.append("]");
		return builder.toString();
	}

}