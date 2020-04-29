package com.genpact.library.beans;

public class LibraryDto {

	public LibraryDto() {
	}

	public LibraryDto(long libraryId, String libraryName) {
		super();
		this.libraryId = libraryId;
		this.libraryName = libraryName;
	}

	private long libraryId;

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
		LibraryDto other = (LibraryDto) obj;
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
		builder.append("LibraryDto [libraryId=").append(libraryId).append(", libraryName=").append(libraryName)
				.append("]");
		return builder.toString();
	}

}
