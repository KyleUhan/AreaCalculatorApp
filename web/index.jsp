<%-- 
    Document   : index
    Created on : Sep 5, 2014, 12:23:18 AM
    Author     : Kyle Uhan
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.TriangleCalculator"%>
<%@page import="model.CircleCalculator"%>
<%@page import="model.RectangleCalculator"%>
<%@page import="model.WebAppCalculator"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shape Calculator</title>
        <link href="styles/style.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="js/js.js" type="text/javascript"></script>
    </head>
    <body>
        <h1>Shape Calculator</h1>
        <br>
        <h2>Welcome!</h2>
        <h4>Lets do some calculating.</h4>
        <br>
        <nav>
            <input id='rectangleRd' class="radio" type="radio" name="shapeCalc" value="rectangle">
            <label for="rectangleRd">Rectangle</label>
            <input id='circleRd' class="radio" type="radio" name="shapeCalc" value="circle">
            <label for="circleRd">Circle</label>
            <input id='triangleRd' class="radio" type="radio" name="shapeCalc" value="triangle">
            <label for="triangleRd">Triangle</label>
        </nav>
        <br>
        <!--RESPONSE TEXT AREA-->
        <div id="response">
            <%
                List<Boolean> selectedItem = new ArrayList();
                WebAppCalculator inputValue = null;
                RectangleCalculator rec = null;
                CircleCalculator circ = null;
                TriangleCalculator tri = null;
                
                //Global vars for js
                int calc = -1;
                double len = 0.0, wid = 0.0, diameter = 0.0, s1 = 0.0, s2 = 0.0, s3 = 0.0;
                //end vars
                try {
                    inputValue = (WebAppCalculator) request.getAttribute("areaCalculator");
                    selectedItem = inputValue.getCalcSelected();
                    out.println("Area: " + inputValue.getArea() + "<br>" + inputValue.getCurrentCalc().toString());
                } catch (Exception e) {
                    out.print("");
                }

                for (int i = 0; i < selectedItem.size(); i++) {
                    if (selectedItem.get(i)) {
                        calc = i;
                    }
                }

                switch (calc) {
                    case 0:
                        rec = (RectangleCalculator) inputValue.getCurrentCalc();
                        len = rec.getLength();
                        wid = rec.getWidth();
                        break;
                    case 1:
                        circ = (CircleCalculator) inputValue.getCurrentCalc();
                        diameter = circ.getDiameter();
                        break;
                    case 2:
                        tri = (TriangleCalculator) inputValue.getCurrentCalc();
                        s1 = tri.getTrianleOne();
                        s2 = tri.getTrianleTwo();
                        s3 = tri.getTriangleThree();
                        break;
                    default:

                }
            %>
        </div>
        <!--END RESPONSE TEXT AREA-->

        <!--MENU/OPTIONS and FORMS AREA-->
        <form id='recForm' name='rectArea' method="POST" action='AreaCalculatorController'>
            <input name="formName" type="hidden" value="rectArea"/>
            <input name="inputValue" type="hidden" value="length"/>
            <input name="inputValue2" type="hidden" value="width"/>

            <div id="squareWrapper">
                <div id='len' class='inputArea'>
                    <p>Length:</p>
                    <input class='input' name='length' type='text' value='' placeholder='000'/>
                </div>
                <div id='wid' class='inputArea'>
                    <p>Width:</p>
                    <input class='input' name='width' type='text' value='' placeholder='000'/>
                </div>
            </div>
            <input class='submit' type="submit" name='submit' value='calculate'/>
        </form>

        <form id='cirForm' name='circleArea' method="POST" action='AreaCalculatorController'>
            <input name="formName" type="hidden" value="circleArea"/>
            <input name="inputValue" type="hidden" value="diameter"/>
            <div id='circle'>
                <div id='circleInput'>
                    <p>diameter: </p>
                    <input class='input' name='diameter' type='text' value='' placeholder='000'/>
                </div>
            </div>
            <input class='submit' type="submit" name='submitCircle' value='calculate'/>
        </form>

        <form id='triForm' name='triangleArea' method="POST" action='AreaCalculatorController'>
            <input name="formName" type="hidden" value="triangleArea"/>
            <input name="inputValue" type="hidden" value="triangle1"/>
            <input name="inputValue2" type="hidden" value="triangle2"/>
            <div id='triangle'>
                <div id='triCover'></div>
                <div id='triInput1'>
                    <p>Side 1: </p>
                    <input class='input' name='triangle1' type='text' value='' placeholder='000'/>
                </div>
                <div id='triInput2'>
                    <p>Side 2:  </p>
                    <input class='input' name='triangle2' type='text' value='' placeholder='000'/>
                </div>
            </div>
            <input class='submit' type="submit" name='submitTriangle' value='calculate'/>
        </form>
        <!--END MENU/OPTIONS and FORMS AREA-->

        <!--RESPONSE SHAPE AREA-->
        <div id='squareResponse'>
            <span id='h'></span>
            <span id='l'></span>
        </div>
        <div id='circleResponse'>
            <span id='diameter'></span>
            <span id='radius'></span>
        </div>
        <div id='triangleResponse'>
            <span id='side1' class='side'></span>
            <span id='side2' class='side'></span>
            <span id='side3' class='side'></span>
        </div>
        <!--END RESPONSE SHAPE AREA-->
        
        <!--GLOBAL VAR FOR EXTERNAL SHEET & SHAPE BUILDERS-->
        <script>
            function buildSquare() {
                var len = '<%=wid%>';
                var wid = '<%=len%>';
                $('#squareResponse').animate({height: len, width: wid}, 500);
                if (wid >= 50 && len >= 50) {
                    $('#l').text(len);
                    $('#h').text(wid);
                }
            }

            function buildCircle() {
                var diam = ('<%= diameter%>');
                rad = diam / 2;
                $('#circleResponse').animate({height: diam, width: diam}, 500);
                $('#diameter').width(diam).animate({'margin-top': (rad - 10) + 'px'}, 500);
                $('#diameter').text(diam + ' d');
                $('#radius').width(rad);
                $('#radius').text(rad + ' r');
            }

            function buildTriangle() {
                var s1 = ('<%=s1%>');
                var s2 = ('<%=s2%>');
                var s3 = ('<%=s3%>');
                var s1Hold = s1, s2Hold = s2, s3Hold = s3;
                var maxTriSize = 200;
                if (s3Hold > maxTriSize) {
                    s3Hold = maxTriSize;
                    s1Hold = maxTriSize;
                }
                $('#triangleResponse').animate({
                    borderWidth: s3Hold
                }, 500).animate({'margin-left': s1Hold + 'px'});
                $('#side3').animate({left: '-'+s1Hold*2+'px'},500);
                $('#side3').text(s3);
            }
            
            var calc = '<%=calc%>';
        </script>
        <!--END GLOBAL VAR FOR EXTERNAL SHEET & SHAPE BUILDERS-->
    </body>
</html>
