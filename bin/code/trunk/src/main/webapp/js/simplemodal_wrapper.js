var htmlArr = new Array();
(function(b) {
	"function" === typeof define && define.amd
			? define(["jquery"], b)
			: b(jQuery)
})(function(b) {
			var closeFn;
			b.dialog = function(h) {
				return b.dialog.impl.init(null, h)
			};
			b.dialog.close = function() {
				(closeFn) ? closeFn() : $.modal.close();
			}
			b.fn.dialog = function(a) {
				return b.dialog.impl.init(this, a)
			};
			b.dialog.impl = {
				d : {},
				init : function(a, h) {
					var eventStr = h.bindEvent || "click";
					if (h.contentUrl && !htmlArr[h.contentUrl]) {
						$.ajax({
									url : h.contentUrl,
									async : false,
									success : function(e) {
										htmlArr[h.contentUrl] = $(e);
									}
								});
					}
					"string" === typeof h.bindNode
							&& (h.bindNode = $(h.bindNode));
					h.bindNode && h.bindNode.on(eventStr, function() {
								dialog_operate(a, h);
							});
					return this
				}
			}
			function dialog_operate(a, h) {
				var m = $('#basic-modal-content');
				var d_c = $("#basic_dialog_content");
				var d_o = $("#basic_dialog_operate");
				var d_t = $("#basic_dialog_title");
				var cf = $('#basic_dialog_confirm');
				var d_f = $('#basic_dialog_form');
				var d_f_c = $('#dialog_form_content');
				var d_t = $('#basic_dialog_title');
				var c_a;
				var d_s;
				var d_o_h = d_o.height() - 6;
				d_t.mousedown(titleMousedown);
				d_f_c.empty();
				if (h) {
					var h_u;
					var h_c
					if (h.contentUrl) {
						h_u = htmlArr[h.contentUrl];
					}
					if (h.content) {
						h_c = $('<div>' + h.content + '</div>');
					}
					c_a = h_u || h_c || a;
					d_f_c.append(c_a);
					// alert(c_a);
					d_f = d_f_c.find('form').length ? d_f_c.find('form') : d_f;
					h.action && d_f.attr('action', h.action);
					h.method && d_f.attr('method', h.method);
					h.title && d_t.html(h.title);
					h.confirmStr
							&& $('#base_dialog_confirm').html(h.confirmStr);
					h.cancelStr && $('#base_dialog_cancel').html(h.cancelStr);
					closeFn = h.close;
					if (h.noButton) {
						d_o.remove();
						d_o_h = 0;
					}
					h.minWidth = h.minWidth || 300;
				}
				m.modal(h);
				if (!h.minHeight) {
					d_c.css('height', 'auto');
					$('#simplemodal-container').css('height', 'auto');
				} else {
					d_c.height(d_c.parent().parent().height() - d_t.height()
							- d_o_h - 10);
				}
				cf.live('click', function() {
					(h && h.submit) ? h.submit() : (d_f.attr('method') ? d_f
							.submit() : $.dialog.close());
						// TODO .. loading img
					});
				$('#basic_dialog_cancel').live('click', function() {
							$.dialog.close();
						});
			}
		});
function titleMousedown(event) {
	var event1 = e(event);
	var d_m = $(event1.target).parent().parent().parent();
	var t_x = event1.mx - d_m.offset().left;
	var t_y = event1.my - d_m.offset().top;
	document.onmousemove = function(event) {
		var event2 = e(event);
		var mx = event2.mx;
		var my = event2.my;
		d_m.offset({
					top : my - t_y,
					left : mx - t_x
				});
	};
}
document.onmouseup = function(event) {
	document.onmousemove = null;
}
function e(event) {
	if (!event) { // 兼容IE浏览器
		event = window.event;
		event.target = event.srcElement;
		event.layerX = event.offsetX;
		event.layerY = event.offsetY;
	}
	event.mx = event.pageX || event.clientX + document.body.scrollLeft; // 计算鼠标指针X轴距离
	event.my = event.pageY || event.clientY + document.body.scrollTop; // 计算鼠标指针Y轴距离
	return event; // 返回标准化的事件对象
}