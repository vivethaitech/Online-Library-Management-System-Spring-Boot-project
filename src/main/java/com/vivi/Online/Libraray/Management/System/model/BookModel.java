package com.vivi.Online.Libraray.Management.System.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookModel {
    
    @NotBlank(message = "Title is mandatory")
    @Size(min = 1, max = 100, message = "Title should be between 1 and 100 characters")
    private String title;
    
    @NotBlank(message = "Author is mandatory")
    @Size(min = 1, max = 50, message = "Author should be between 1 and 50 characters")
    private String author;
    
    @NotNull(message = "Available status is mandatory")
    private boolean available;
    
    @NotBlank(message = "Publisher is mandatory")
    @Size(min = 1, max = 50, message = "Publisher should be between 1 and 50 characters")
    private String publisher;
    
    @NotNull(message = "Published year is mandatory")
    @Min(value = 1450, message = "Published year should not be before 1450")
    @Max(value = 2024, message = "Published year should not be after 2024")
    private Integer publishedYear;

  
    public BookModel(
			@NotBlank(message = "Title is mandatory") @Size(min = 1, max = 100, message = "Title should be between 1 and 100 characters") String title,
			@NotBlank(message = "Author is mandatory") @Size(min = 1, max = 50, message = "Author should be between 1 and 50 characters") String author,
			@NotBlank(message = "Publisher is mandatory") @Size(min = 1, max = 50, message = "Publisher should be between 1 and 50 characters") String publisher,
			@NotNull(message = "Published year is mandatory") @Min(value = 1450, message = "Published year should not be before 1450") @Max(value = 2024, message = "Published year should not be after 2024") Integer publishedYear, 
            @NotNull(message = "Available status is mandatory") Boolean available){
		this.title = title;
		this.author = author;
		this.available=available;
		this.publisher = publisher;
		this.publishedYear = publishedYear;
	}

    
	public BookModel() {
		
	}


	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }


	public boolean getAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}


	@Override
	public String toString() {
		return "BookModel [title=" + title + ", author=" + author + ", available=" + available + ", publisher="
				+ publisher + ", publishedYear=" + publishedYear + "]";
	}


    
}

