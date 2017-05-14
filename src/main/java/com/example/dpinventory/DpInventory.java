
package com.example.dpinventory;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


public class DpInventory {

   
    private Integer dpId;
  
    private Integer tableSize;
   
   

  

    /**
     * 
     * @return
     *     The dpId
     */
  
    public Integer getDpId() {
        return dpId;
    }

    /**
     * 
     * @param dpId
     *     The dpId
     */
  
    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    /**
     * 
     * @return
     *     The tableSize
     */
 
    public Integer getTableSize() {
        return tableSize;
    }

    /**
     * 
     * @param tableSize
     *     The tableSize
     */
   
    public void setTableSize(Integer tableSize) {
        this.tableSize = tableSize;
    }

	@Override
	public String toString() {
		return "{\"dpId\":" + dpId + ",\"tableSize\":" + tableSize + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dpId == null) ? 0 : dpId.hashCode());
		result = prime * result + ((tableSize == null) ? 0 : tableSize.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DpInventory other = (DpInventory) obj;
		if (dpId == null) {
			if (other.dpId != null)
				return false;
		} else if (!dpId.equals(other.dpId))
			return false;
		if (tableSize == null) {
			if (other.tableSize != null)
				return false;
		} else if (!tableSize.equals(other.tableSize))
			return false;
		return true;
	}
    

}