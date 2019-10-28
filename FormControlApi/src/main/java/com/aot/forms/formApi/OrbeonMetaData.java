package com.aot.forms.formApi;
import javax.persistence.Entity;
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
	    private String camunda_id;
	    private String data;
	    private String status;
	    private String column_1;
	    private String column_2;
	    private String column_3;
	    private String column_4;
	    
	    
	    
	    public String getCamunda_id() {
			return camunda_id;
		}

		public void setCamunda_id(String camunda_id) {
			this.camunda_id = camunda_id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getColumn_1() {
			return column_1;
		}

		public void setColumn_1(String column_1) {
			this.column_1 = column_1;
		}

		public String getColumn_2() {
			return column_2;
		}

		public void setColumn_2(String column_2) {
			this.column_2 = column_2;
		}

		public String getColumn_3() {
			return column_3;
		}

		public void setColumn_3(String column_3) {
			this.column_3 = column_3;
		}

		public String getColumn_4() {
			return column_4;
		}

		public void setColumn_4(String column_4) {
			this.column_4 = column_4;
		}

		public OrbeonMetaData() {
	    }

	    public OrbeonMetaData(String documentid, String username, String data,
	    		String camunda_id, String status, String column_1, String column_2, String column_3, String column_4) {

	        this.documentid = documentid;
	        this.username = username;
	        this.data = data;
	        this.camunda_id = camunda_id;
	        this.status = status;
	        this.column_1 = column_1;
	        this.column_2 = column_2;
	        this.column_3 = column_3;
	        this.column_4 = column_4;
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
