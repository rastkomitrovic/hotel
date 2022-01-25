let counter = 1
let arraySelected = []

function addRoom(e) {
    e.preventDefault()
    const select = document.getElementById("roomTypes")
    const selectedText = select.options[select.selectedIndex].innerText

    const object = {
        id: parseInt(select.value),
        textValue: selectedText,
        numberValue: 1
    }

    let notFound = true

    arraySelected.forEach(el => {
        if (el.id === object.id) {
            el.numberValue++
            notFound = false
        }
    })

    if (notFound) {
        arraySelected.push(object)
    }
    createViewForSelectedRooms()
    recreateHiddenInputs()
}

function createViewForSelectedRooms() {
    document.getElementById("infoSelectedRooms").innerHTML= ''
    let str = ''
    arraySelected.forEach(el => {
        str+= `<div>
                <div class="info">
                    ${el.textValue} - ${el.numberValue}
               </div>
               <div class="actions">
                    <button type="button" onclick="changeType(${el.id}, 'add')">plus</button>
                    <button type="button" onclick="changeType(${el.id},'minus')">minus</button>
               </div>
               </div>`
    })
    document.getElementById("infoSelectedRooms").innerHTML+= str
}

function changeType(elId,type) {
    arraySelected.forEach( (el, i) =>{
        if(el.id === elId){
            if(type ==='add'){
                el.numberValue++
            }else{
                el.numberValue--
                if(el.numberValue<=0)
                    arraySelected.splice(i, 1)
            }
        }
    })

    createViewForSelectedRooms()
    recreateHiddenInputs()
}

function recreateHiddenInputs() {
    document.getElementById("hidden-inputs").innerHTML = ""

    arraySelected.forEach(el =>{
        //const selected =`<input checked hidden id="rooms${counter}" name="rooms" multiple="true" type="checkbox" value="${select.value}">`
        for(let i=0; i<el.numberValue;i++){
            const input = document.createElement("input")
            input.setAttribute("checked", "")
            input.setAttribute("hidden", "")
            input.setAttribute("id", `rooms${counter}`)
            input.setAttribute("name", "rooms")
            input.setAttribute("multiple", "true")
            input.setAttribute("type", "checkbox")
            input.setAttribute("value", `${el.id}`)
            counter++
            document.querySelector("#hidden-inputs").appendChild(input)
        }
    })
}