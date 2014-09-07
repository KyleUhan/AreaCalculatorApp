/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * High level Calculator Service Class
 *
 * @author Kyle Uhan
 */
public class WebAppCalculator {

    private static final String AREA_CALC = "areaCalculator";
    private static final String INPUT = "input";
    private static final String FORM_NAME = "formName";
    private static final String[] KEY_FORM_ID = {"rec", "cir", "tri"};

    private HttpServletRequest request;
    private CalculateAreaStrategy currentCalc;
    private List<CalculateAreaStrategy> calculator = new ArrayList();
    private List<Boolean> calcSelected = new ArrayList();

    public WebAppCalculator() {
    }

    /**
     * Constructor allows servlet request object to be passed in. Currently,
     * calculator objects are being set manually - switch to allow user to pass in
     *
     *
     * @param request
     */
    public WebAppCalculator(HttpServletRequest request) {
        setRequest(request);
        getCalculator().add(new RectangleCalculator());
        getCalculator().add(new CircleCalculator());
        getCalculator().add(new TriangleCalculator());
        getCalculator().stream().forEach((_item) -> {
            getCalcSelected().add(Boolean.FALSE);
        });
    }

    public String getArea() {
        clearSelectedCalc();
        ArrayList<String> inputList = getInputValues();
        String formName = getRequest().getParameter(FORM_NAME);
        String area = "";
        for (int i = 0; i < KEY_FORM_ID.length; i++) {
            if (formName.contains(KEY_FORM_ID[i])) {
                area = getCalculator().get(i).getArea(inputList) + "";
                setCurrentCalc(i);
                setCalcSelectedValue(i);
            }
        }
        return area;
    }

    private void clearSelectedCalc() {
        for (Boolean b : getCalcSelected()) {
            b = Boolean.FALSE;
        }
    }

    private void setCalcSelectedValue(final int calcPosition) {
        getCalcSelected().set(calcPosition, Boolean.TRUE);
    }

    private String findCalcType(String paramenter) {
        String formName = getRequest().getParameter(paramenter);
        return formName;
    }

    private Map<String, String[]> findAll() {
        return request.getParameterMap();
    }

    private ArrayList<String> getInputValues() {
        ArrayList<String> inputList = new ArrayList<>();
        getRequest().getAttribute(AREA_CALC);
        findAll().keySet().stream().filter((s) -> (s.contains(INPUT))).forEach((s) -> {
            inputList.add(getRequest().getParameter(findCalcType(s)));
        });
        return inputList;
    }

    private void setCurrentCalc(final int calcPosition) {
        setCurrentCalc(getCalculator().get(calcPosition));
    }

    public final HttpServletRequest getRequest() {
        return request;
    }

    public final void setRequest(final HttpServletRequest request) {
        this.request = request;
    }

    public final List<CalculateAreaStrategy> getCalculator() {
        return calculator;
    }

    public void setCalculator(List<CalculateAreaStrategy> calculator) {
        this.calculator = calculator;
    }

    public CalculateAreaStrategy getCurrentCalc() {
        return currentCalc;
    }

    public void setCurrentCalc(CalculateAreaStrategy currentCalc) {
        this.currentCalc = currentCalc;
    }

    public final List<Boolean> getCalcSelected() {
        return calcSelected;
    }

}
