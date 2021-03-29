package com.BookKeeping.V1.Vo.Conpoent.comboBox;

import java.util.ArrayList;
import java.util.List;

import com.BookKeeping.V1.Vo.Conpoent.ElementProperty;
import com.BookKeeping.V1.Vo.Conpoent.SelectListItem;

/**
 * 下拉選單.
 * */
public class ComboBox extends ElementProperty {
	
	private static final long serialVersionUID = 1L;

	/** The options. */
    private List<SelectListItem> options = new ArrayList<SelectListItem>();
    
    /** The Constant DEFAULT_LABEL. */
    private static final String DEFAULT_LABEL = "-----請選擇-----";
    
    /** The Constant DEFAULT_VALUE. */
    private static final String DEFAULT_VALUE = "none";


	public ComboBox() {}

    /**
     * 新增預設的ComboItem.
     */
    public void addDefaultComboItem() {
        this.add(DEFAULT_LABEL, DEFAULT_VALUE);
    }
    
    /**
     * Adds the.
     *
     * @param sLabel            the s label.
     * @param sValue            the s value.
     */
    public void add(String sLabel, String sValue) {
        SelectListItem item = new SelectListItem(sLabel, sValue);
        add(item);
    }
    
    /**
     * Adds the.
     *
     * @param sLabel the s label
     * @param sValue the s value
     * @param selected the selected
     */
    public void add(String sLabel, String sValue, Boolean selected) {
        SelectListItem item = new SelectListItem(sLabel, sValue, selected);
        add(item);
        // 設置下拉選單選中值
        if(selected) {
        	super.selectedVal = sValue;
        }
    }

    /**
     * Adds the.
     * @param item
     *            the item.
     */
    public void add(SelectListItem item) {
        options.add(item);
    }

	/**
	 * Gets the options.
	 *
	 * @return the options
	 */
	public List<SelectListItem> getOptions() {
		return options;
	}

	/**
	 * Sets the options.
	 *
	 * @param options the new options
	 */
	public void setOptions(List<SelectListItem> options) {
		this.options = options;
	}

	public String getSelectedVal() {
		return selectedVal;
	}

	public void setSelectedVal(String selectedVal) {
		this.selectedVal = selectedVal;
	}

}
