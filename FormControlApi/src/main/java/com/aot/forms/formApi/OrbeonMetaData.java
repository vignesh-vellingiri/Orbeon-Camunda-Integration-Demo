package com.aot.forms.formApi;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "orbeon_meta_data")
public class OrbeonMetaData {
	    @Id
	    private String documentid;

	    private String username;
	    private String data;

	    public OrbeonMetaData() {
	    }

	    public OrbeonMetaData(String documentid, String username, String data) {

	        this.documentid = documentid;
	        this.username = username;
	        this.data = data;
	    }

	    public String getDocumentid() {
			return documentid;
		}

		public void setDocumentid(String documentid) {
			this.documentid = documentid;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		@Override
	    public String toString() {
	        final StringBuilder sb = new StringBuilder("City{");
	        sb.append("documentid=").append(documentid);
	        sb.append(", username='").append(username).append('\'');
	        sb.append(", data=").append(data);
	        sb.append('}');
	        return sb.toString();
	    }
	
}
