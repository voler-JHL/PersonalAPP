!function(){function j(d,e,f){if(d&&"string"==typeof d){b++;var g={name:d};"object"==typeof e&&"{}"!==JSON.stringify(e)&&(g.params=e),"function"==typeof f&&(g.callbackId=a+b,c[g.callbackId]={name:d,params:e,callback:f}),k(g)}}function k(a){f.length||setTimeout(function(){l("messageready")},0),f.push(a)}function l(a){p("notify://"+a)}function m(a,b){p("transfer://"+a+"/"+(b||""))}function n(a,b){return a&&"string"==typeof a&&"function"==typeof b?(d[a]=b,function(){o(a)}):h}function o(a){a&&d[a]&&delete d[a]}function p(a){e.push(a),g||q()}function q(){var a=e.shift();a?(g=!0,i.src=a):g=!1}function r(){q()}function s(){var a=encodeURIComponent(JSON.stringify(f));return f=[],window.disableJavaScriptInterface?(m("message",a),void 0):a}function t(a){var b,e,f,g;a&&(b=a.responseId,e=a.callbackId,b?(f=c[b]||{},"string"==typeof b&&"function"==typeof f.callback&&(f.callback(a.result),delete c[b])):"string"==typeof a.name&&(g=d[a.name],g&&"function"==typeof g&&(e?g(a.params,function(a){k({responseId:e,result:a})}):g(a.params))))}var a="callback_",b=0,c={},d={},e=[],f=[],g=!1,h=function(){},i=document.createElement("iframe");i.id="newsappJsBridgeIframe",i.style.display="none",document.body.appendChild(i),window.handleConfirmFromNative=r,window.handleMessageFromNative=t,window.fetchMessage=s,window.newsappJsBridge={invoke:j,notify:l,bind:n,unbind:o}}();