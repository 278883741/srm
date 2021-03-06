$.extend(!0, $.fn.dataTable.defaults, {
    sDom: "<'row'<'col-sm-12'<'pull-right'f><'pull-left'l>r<'clearfix'>>>t<'row'<'col-sm-12'<'pull-left'i><'pull-right'p><'clearfix'>>>",
    sPaginationType: "bs_normal",
    oLanguage: {
        sLengthMenu: "Show _MENU_ Rows",
        sSearch: ""
    }
}),
    $.extend($.fn.dataTableExt.oStdClasses, {
        sWrapper: "dataTables_wrapper form-inline"
    }),
    $.fn.dataTableExt.oApi.fnPagingInfo = function(t) {
        return {
            iStart: t._iDisplayStart,
            iEnd: t.fnDisplayEnd(),
            iLength: t._iDisplayLength,
            iTotal: t.fnRecordsTotal(),
            iFilteredTotal: t.fnRecordsDisplay(),
            iPage: -1 === t._iDisplayLength ? 0: Math.ceil(t._iDisplayStart / t._iDisplayLength),
            iTotalPages: -1 === t._iDisplayLength ? 0: Math.ceil(t.fnRecordsDisplay() / t._iDisplayLength)
        }
    },
    $.extend($.fn.dataTableExt.oPagination, {
        bs_normal: {
            fnInit: function(t, e, i) {
                var n = t.oLanguage.oPaginate,
                r = t.oClasses,
                a = function(e) {
                    t.oApi._fnPageChange(t, e.data.action) && i(t)
                };
            $(e).append('<ul class="pagination"><li class="disabled"><a  tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageFirst + '">' + n.sFirst + "</a></li>" + '<li class="disabled"><a  tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPagePrevious + '">' + n.sPrevious + "</a></li>" + '<li><a tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageNext + '">' + n.sNext + "</a></li>" + '<li><a tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageLast + '">' + n.sLast + "</a></li>" + "</ul>");
            var o = $("a", e),
                s = o[0],
                l = o[1],
                h = o[2],
                c = o[3];
            t.oApi._fnBindAction(s, {
                    action: "first"
                },
                a),
                t.oApi._fnBindAction(l, {
                        action: "previous"
                    },
                    a),
                t.oApi._fnBindAction(h, {
                        action: "next"
                    },
                    a),
                t.oApi._fnBindAction(c, {
                        action: "last"
                    },
                    a),
            t.aanFeatures.p || (e.id = t.sTableId + "_paginate", s.id = t.sTableId + "_first", l.id = t.sTableId + "_previous", h.id = t.sTableId + "_next", c.id = t.sTableId + "_last")
        },
            fnUpdate: function(t, e) {
                var i,
                    n,
                    r,
                    a,
                    o,
                    s,
                    l = 5,
                    h = t.oInstance.fnPagingInfo(),
                    c = t.aanFeatures.p,
                    u = Math.floor(l / 2);
                for (h.iTotalPages < l ? (o = 1, s = h.iTotalPages) : h.iPage <= u ? (o = 1, s = l) : h.iPage >= h.iTotalPages - u ? (o = h.iTotalPages - l + 1, s = h.iTotalPages) : (o = h.iPage - u + 1, s = o + l - 1), i = 0, n = c.length; n > i; i++) {
                    for ($("li:gt(1)", c[i]).filter(":not(li:eq(-2))").filter(":not(li:eq(-1))").remove(), r = o; s >= r; r++) a = r == h.iPage + 1 ? 'class="active"': "",
                        $("<li " + a + '><a href="#">' + r + "</a></li>").insertBefore($("li:eq(-2)", c[i])[0]).bind("click",
                            function(i) {
                                i.preventDefault(),
                                    t._iDisplayStart = (parseInt($("a", this).text(), 10) - 1) * h.iLength,
                                    e(t)
                            });
                    0 === h.iPage ? ($("li:eq(0)", c[i]).addClass("disabled"),$("li:eq(1)", c[i]).addClass("disabled")) : ($("li:eq(0)", c[i]).removeClass("disabled"),$("li:eq(1)", c[i]).removeClass("disabled")),
                        h.iPage === h.iTotalPages - 1 || 0 === h.iTotalPages ? ($("li:eq(-1)", c[i]).addClass("disabled"),$("li:eq(-2)", c[i]).addClass("disabled")) : ($("li:eq(-1)", c[i]).removeClass("disabled"),$("li:eq(-2)", c[i]).removeClass("disabled"))
                }
            }
        },
        bs_two_button: {
            fnInit: function(t, e, i) {
                var n = t.oLanguage.oPaginate;
                t.oClasses;
                var r = function(e) {
                        t.oApi._fnPageChange(t, e.data.action) && i(t)
                    },
                    a = '<ul class="pagination"><li class="prev"><a class="' + t.oClasses.sPagePrevDisabled + '" tabindex="' + t.iTabIndex + '" role="button"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;' + n.sPrevious + "</a></li>" + '<li class="next"><a class="' + t.oClasses.sPageNextDisabled + '" tabindex="' + t.iTabIndex + '" role="button">' + n.sNext + '&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></a></li>' + "</ul>";
                $(e).append(a);
                var o = $("a", e),
                    s = o[0],
                    l = o[1];
                t.oApi._fnBindAction(s, {
                        action: "previous"
                    },
                    r),
                    t.oApi._fnBindAction(l, {
                            action: "next"
                        },
                        r),
                t.aanFeatures.p || (e.id = t.sTableId + "_paginate", s.id = t.sTableId + "_previous", l.id = t.sTableId + "_next", s.setAttribute("aria-controls", t.sTableId), l.setAttribute("aria-controls", t.sTableId))
            },
            fnUpdate: function(t) {
                if (t.aanFeatures.p) {
                    var e = t.oInstance.fnPagingInfo();
                    t.oClasses;
                    for (var i = t.aanFeatures.p, n = 0, r = i.length; r > n; n++) 0 === e.iPage ? $("li:first", i[n]).addClass("disabled") : $("li:first", i[n]).removeClass("disabled"),
                        e.iPage === e.iTotalPages - 1 || 0 === e.iTotalPages ? $("li:last", i[n]).addClass("disabled") : $("li:last", i[n]).removeClass("disabled")
                }
            }
        },
        bs_four_button: {
            fnInit: function(t, e, i) {
                var n = t.oLanguage.oPaginate,
                    r = t.oClasses,
                    a = function(e) {
                        t.oApi._fnPageChange(t, e.data.action) && i(t)
                    };
                $(e).append('<ul class="pagination"><li class="disabled"><a  tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageFirst + '"><span class="glyphicon glyphicon-backward"></span>&nbsp;' + n.sFirst + "</a></li>" + '<li class="disabled"><a  tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPagePrevious + '"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;' + n.sPrevious + "</a></li>" + '<li><a tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageNext + '">' + n.sNext + '&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></a></li>' + '<li><a tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageLast + '">' + n.sLast + '&nbsp;<span class="glyphicon glyphicon-forward"></span></a></li>' + "</ul>");
                var o = $("a", e),
                    s = o[0],
                    l = o[1],
                    h = o[2],
                    c = o[3];
                t.oApi._fnBindAction(s, {
                        action: "first"
                    },
                    a),
                    t.oApi._fnBindAction(l, {
                            action: "previous"
                        },
                        a),
                    t.oApi._fnBindAction(h, {
                            action: "next"
                        },
                        a),
                    t.oApi._fnBindAction(c, {
                            action: "last"
                        },
                        a),
                t.aanFeatures.p || (e.id = t.sTableId + "_paginate", s.id = t.sTableId + "_first", l.id = t.sTableId + "_previous", h.id = t.sTableId + "_next", c.id = t.sTableId + "_last")
            },
            fnUpdate: function(t) {
                if (t.aanFeatures.p) {
                    var e = t.oInstance.fnPagingInfo();
                    t.oClasses;
                    for (var i = t.aanFeatures.p, n = 0, r = i.length; r > n; n++) 0 === e.iPage ? ($("li:eq(0)", i[n]).addClass("disabled"), $("li:eq(1)", i[n]).addClass("disabled")) : ($("li:eq(0)", i[n]).removeClass("disabled"), $("li:eq(1)", i[n]).removeClass("disabled")),
                        e.iPage === e.iTotalPages - 1 || 0 === e.iTotalPages ? ($("li:eq(2)", i[n]).addClass("disabled"), $("li:eq(3)", i[n]).addClass("disabled")) : ($("li:eq(2)", i[n]).removeClass("disabled"), $("li:eq(3)", i[n]).removeClass("disabled"))
                }
            }
        },
        bs_full: {
            fnInit: function(t, e, i) {
                var n = t.oLanguage.oPaginate,
                    r = t.oClasses,
                    a = function(e) {
                        t.oApi._fnPageChange(t, e.data.action) && i(t)
                    };
                $(e).append('<ul class="pagination"><li class="disabled"><a  tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageFirst + '">' + n.sFirst + "</a></li>" + '<li class="disabled"><a  tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPagePrevious + '">' + n.sPrevious + "</a></li>" + '<li><a tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageNext + '">' + n.sNext + "</a></li>" + '<li><a tabindex="' + t.iTabIndex + '" class="' + r.sPageButton + " " + r.sPageLast + '">' + n.sLast + "</a></li>" + "</ul>");
                var o = $("a", e),
                    s = o[0],
                    l = o[1],
                    h = o[2],
                    c = o[3];
                t.oApi._fnBindAction(s, {
                        action: "first"
                    },
                    a),
                    t.oApi._fnBindAction(l, {
                            action: "previous"
                        },
                        a),
                    t.oApi._fnBindAction(h, {
                            action: "next"
                        },
                        a),
                    t.oApi._fnBindAction(c, {
                            action: "last"
                        },
                        a),
                t.aanFeatures.p || (e.id = t.sTableId + "_paginate", s.id = t.sTableId + "_first", l.id = t.sTableId + "_previous", h.id = t.sTableId + "_next", c.id = t.sTableId + "_last")
            },
            fnUpdate: function(t, e) {
                if (t.aanFeatures.p) {
                    var i,
                        n,
                        r,
                        a,
                        o = t.oInstance.fnPagingInfo(),
                        s = $.fn.dataTableExt.oPagination.iFullNumbersShowPages,
                        l = Math.floor(s / 2),
                        h = Math.ceil(t.fnRecordsDisplay() / t._iDisplayLength),
                        c = Math.ceil(t._iDisplayStart / t._iDisplayLength) + 1,
                        u = "";
                    t.oClasses;
                    var d,
                        p = t.aanFeatures.p;
                    for ( - 1 === t._iDisplayLength ? (i = 1, n = 1, c = 1) : s > h ? (i = 1, n = h) : l >= c ? (i = 1, n = s) : c >= h - l ? (i = h - s + 1, n = h) : (i = c - Math.ceil(s / 2) + 1, n = i + s - 1), r = i; n >= r; r++) u += c !== r ? '<li><a tabindex="' + t.iTabIndex + '">' + t.fnFormatNumber(r) + "</a></li>": '<li class="active"><a tabindex="' + t.iTabIndex + '">' + t.fnFormatNumber(r) + "</a></li>";
                    for (r = 0, a = p.length; a > r; r++) d = p[r],
                    d.hasChildNodes() && ($("li:gt(1)", p[r]).filter(":not(li:eq(-2))").filter(":not(li:eq(-1))").remove(), 0 === o.iPage ? ($("li:eq(0)", p[r]).addClass("disabled"), $("li:eq(1)", p[r]).addClass("disabled")) : ($("li:eq(0)", p[r]).removeClass("disabled"), $("li:eq(1)", p[r]).removeClass("disabled")), o.iPage === o.iTotalPages - 1 || 0 === o.iTotalPages ? ($("li:eq(-1)", p[r]).addClass("disabled"), $("li:eq(-2)", p[r]).addClass("disabled")) : ($("li:eq(-1)", p[r]).removeClass("disabled"), $("li:eq(-2)", p[r]).removeClass("disabled")), $(u).insertBefore("li:eq(-2)", p[r]).bind("click",
                        function(i) {
                            i.preventDefault(),
                                t._iDisplayStart = (parseInt($("a", this).text(), 10) - 1) * o.iLength,
                                e(t)
                        }))
                }
            }
        }
    }),
$.fn.DataTable.TableTools && ($.extend(!0, $.fn.DataTable.TableTools.classes, {
    container: "DTTT btn-group",
    buttons: {
        normal: "btn",
        disabled: "disabled"
    },
    collection: {
        container: "DTTT_dropdown dropdown-menu",
        buttons: {
            normal: "",
            disabled: "disabled"
        }
    },
    print: {
        info: "DTTT_print_info modal"
    },
    select: {
        row: "active"
    }
}), $.extend(!0, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
    collection: {
        container: "ul",
        button: "li",
        liner: "a"
    }
}));