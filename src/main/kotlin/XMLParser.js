export function parseXml(xml, arrayTags) {
    let dom = null;
    if (window.DOMParser) dom = (new DOMParser()).parseFromString(xml, "text/xml");
    else throw new Error("cannot parse xml string!");

    function parseNode(xmlNode, result) {
        if (xmlNode.nodeName === "#text") {
            let v = xmlNode.nodeValue;
            if (v.trim()) result['#text'] = v;
            return;
        }
        let jsonNode = {},
            existing = result[xmlNode.nodeName];
        if (existing) {
            if (!Array.isArray(existing)) result[xmlNode.nodeName] = [existing, jsonNode];
            else result[xmlNode.nodeName].push(jsonNode);
        }
        else {
            if (arrayTags && arrayTags.indexOf(xmlNode.nodeName) !== -1) result[xmlNode.nodeName] = [jsonNode];
            else result[xmlNode.nodeName] = jsonNode;
        }

        if (xmlNode.attributes) for (let attribute of xmlNode.attributes) jsonNode[attribute.nodeName] = attribute.nodeValue;

        for (let node of xmlNode.childNodes) parseNode(node, jsonNode);
    }

    let result = {};
    for (let node of dom.childNodes) parseNode(node, result);
    result = result[dom.childNodes[0].nodeName]
    return JSON.stringify(result);
}

export function loginJS(){
    let ip = window.prompt("Введите IP адрес:");
    let port = window.prompt("Введите порт:");
    let login = window.prompt("Введите логин:");
    let password = window.prompt("Введите пароль:");
    return ip + "," + port + "," + login + "," + password
}

export function RGB(decimalcolorcode)
{
    var color = (decimalcolorcode); // use the property type or put a decimal color value.
    var Rr = (color & 0xff0000 ) >> 16; // get red color by bitwise operation
    var Gg = (color & 0x00ff00) >> 8; // get green color by bitwise operation
    var Bb = (color & 0x0000ff); // get blue color by bitwise operation
    var rgb = Rr + "," + Gg + "," + Bb
    if (rgb == "240,240,240") return "0,0,0"
    return rgb;
}