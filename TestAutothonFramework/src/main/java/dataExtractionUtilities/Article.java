package dataExtractionUtilities;

import java.util.List;

public class Article {
	private Long id;
	private String title;
	private List<String> tags;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", tags=" + tags + "]";
	}
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

}
