
let arraySelectedRooms = []

let arraySelectedServices = []

window.onload = function () {
    let listRooms = document.getElementsByName("rooms")
    listRooms.forEach(el =>{
        const object = {
            id: parseInt(el.value),
            textValue: el.getAttribute("value-for-label"),
            numberValue: 1,
            pricePerDay: parseFloat(el.getAttribute("price-per-day"))
        }

        let notFound = true

        arraySelectedRooms.forEach(el => {
            if (el.id === object.id) {
                el.numberValue++
                notFound = false
            }
        })

        if (notFound) {
            arraySelectedRooms.push(object)
        }
    })

    let listServices = document.getElementsByName("reservationServices")
    listServices.forEach(el =>{
        const object = {
            id: parseInt(el.value),
            textValue: el.getAttribute("value-for-label"),
            numberValue: 1,
            pricePerDay: parseFloat(el.getAttribute("price-per-use"))
        }

        let notFound = true

        arraySelectedServices.forEach(el => {
            if (el.id === object.id) {
                el.numberValue++
                notFound = false
            }
        })

        if (notFound) {
            arraySelectedServices.push(object)
        }
    })

    createViewForSelectedRooms()
    createViewForSelectedServices()
}
function addRoom() {
    //e.preventDefault() // e je bio parametar addRoom funkcije i na jsp-u se prosledjuje kao event
    const select = document.getElementById("roomTypes")
    const selectedText = select.options[select.selectedIndex].innerText

    const object = {
        id: parseInt(select.value),
        textValue: selectedText,
        numberValue: 1,
        pricePerDay: parseFloat(select.options[select.selectedIndex].getAttribute("price-per-day"))
    }

    let notFound = true

    arraySelectedRooms.forEach(el => {
        if (el.id === object.id) {
            el.numberValue++
            notFound = false
        }
    })

    if (notFound) {
        arraySelectedRooms.push(object)
    }
    createViewForSelectedRooms()
    recreateHiddenInputsForRooms()
}

function createViewForSelectedRooms() {
    console.log("aa")
    document.getElementById("infoSelectedRooms").innerHTML = ''
    let str = ''
    arraySelectedRooms.forEach(el => {
        str += `<div>
                <div class="info">
                    ${el.textValue} - X${el.numberValue}
               </div>
               <div class="actions">
                    <button type="button" onclick="changeTypeRooms(${el.id}, 'add')">plus</button>
                    <button type="button" onclick="changeTypeRooms(${el.id},'minus')">minus</button>
               </div>
               </div>`
    })
    document.getElementById("infoSelectedRooms").innerHTML += str
}

function changeTypeRooms(elId, type) {
    arraySelectedRooms.forEach((el, i) => {
        if (el.id === elId) {
            if (type === 'add') {
                el.numberValue++
            } else {
                el.numberValue--
                if (el.numberValue <= 0)
                    arraySelectedRooms.splice(i, 1)
            }
        }
    })

    createViewForSelectedRooms()
    recreateHiddenInputsForRooms()
}

function recreateHiddenInputsForRooms() {
    document.getElementById("hidden-inputs-rooms").innerHTML = ""

    arraySelectedRooms.forEach(el => {
        for (let i = 0; i < el.numberValue; i++) {
            const input = document.createElement("input")
            input.setAttribute("checked", "")
            input.setAttribute("hidden", "")
            input.setAttribute("name", "rooms")
            input.setAttribute("multiple", "")
            input.setAttribute("type", "checkbox")
            input.setAttribute("value", `${el.id}`)
            document.querySelector("#hidden-inputs-rooms").appendChild(input)
        }
    })
}

function addService() {
    const select = document.getElementById("services")
    const selectedText = select.options[select.selectedIndex].innerText

    const object = {
        id: parseInt(select.value),
        textValue: selectedText,
        numberValue: 1,
        pricePerDay: parseFloat(select.options[select.selectedIndex].getAttribute("price-per-use"))
    }

    let notFound = true

    arraySelectedServices.forEach(el => {
        if (el.id === object.id) {
            el.numberValue++
            notFound = false
        }
    })

    if (notFound) {
        arraySelectedServices.push(object)
    }
    createViewForSelectedServices()
    recreateHiddenInputsForServices()
}

function createViewForSelectedServices() {
    console.log("bb")
    document.getElementById("infoSelectedServices").innerHTML = ''
    let str = ''
    arraySelectedServices.forEach(el => {
        str += `<div>
                <div class="info">
                    ${el.textValue} - X${el.numberValue}
               </div>
               <div class="actions">
                    <button type="button" onclick="changeTypeServices(${el.id}, 'add')">plus</button>
                    <button type="button" onclick="changeTypeServices(${el.id},'minus')">minus</button>
               </div>
               </div>`
    })
    document.getElementById("infoSelectedServices").innerHTML += str
}

function changeTypeServices(elId, type) {
    arraySelectedServices.forEach((el, i) => {
        if (el.id === elId) {
            if (type === 'add') {
                el.numberValue++
            } else {
                el.numberValue--
                if (el.numberValue <= 0)
                    arraySelectedServices.splice(i, 1)
            }
        }
    })

    createViewForSelectedServices()
    recreateHiddenInputsForServices()
}

function recreateHiddenInputsForServices() {
    document.getElementById("hidden-inputs-services").innerHTML = ""

    arraySelectedServices.forEach(el => {
        for (let i = 0; i < el.numberValue; i++) {
            const input = document.createElement("input")
            input.setAttribute("checked", "")
            input.setAttribute("hidden", "")
            input.setAttribute("name", "reservationServices")
            input.setAttribute("multiple", "true")
            input.setAttribute("type", "checkbox")
            input.setAttribute("value", `${el.id}`)
            document.querySelector("#hidden-inputs-services").appendChild(input)
        }
    })
}
