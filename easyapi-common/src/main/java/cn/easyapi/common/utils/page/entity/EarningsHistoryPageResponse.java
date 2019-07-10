package cn.easyapi.common.utils.page.entity;

import cn.easyapi.common.utils.page.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class EarningsHistoryPageResponse extends PageResponse {
    private String totalIncome;

    public EarningsHistoryPageResponse(Page page, String totalIncome) {
        super(page);
        this.totalIncome = totalIncome;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
}
