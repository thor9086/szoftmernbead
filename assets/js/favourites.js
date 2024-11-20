document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/drinks')   //Itt kéred le az kedvenceket
        .then(response => response.json())
        .then(data => {
            const drinksContainer = document.getElementById('drinks-container');
            data.forEach(drink => {
                const drinkDiv = document.createElement('div');
                drinkDiv.className = 'card';
                drinkDiv.innerHTML = `
                    <h3>${drink.name}</h3>
                    <p>${drink.description}</p>   
                `;  //a drink.description nem tudom hogy kell e xd
                drinksContainer.appendChild(drinkDiv);
            });
        })
        .catch(error => console.error('Error fetching data:', error));
});