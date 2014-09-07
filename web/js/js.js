/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {

//INPUT MENU/OPTIONS //

    switch (calc) {
        case '0':
            buildSquare();
            break;
        case '1':
            buildCircle();
            break;
        case '2':
            buildTriangle();
            break;
        default:
            //alert("default");
    }

    function hideForms() {
        $('#recForm').hide();
        $('#cirForm').hide();
        $('#triForm').hide();
    }
    hideForms();

    function hideResponse() {
        $('#response').hide();
        $('#squareResponse').hide();
        $('#circleResponse').hide();
        $('#triangleResponse').hide();
    }

    $('input:radio[name="shapeCalc"]').change(function() {
        hideForms();
        hideResponse();
        var name = $(this).val();
        switch (name) {
            case "rectangle":
                $('#recForm').show('slow');
                break;
            case "circle":
                $('#cirForm').show('slow');
                break;
            case "triangle":
                $('#triForm').show('slow');
                break;
            default:
                alert("whoops");
        }
    });

});


