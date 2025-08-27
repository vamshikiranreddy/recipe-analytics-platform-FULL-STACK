// Replace the filterRecipes function with this:
async function filterRecipes() {
    const searchTerm = document.getElementById('search-input').value;
    const activeFilter = document.querySelector('.filter-btn.active').dataset.filter;
    
    try {
        const response = await fetch(`/api/recipes/search?q=${encodeURIComponent(searchTerm)}&category=${activeFilter}`);
        const recipes = await response.json();
        
        // Clear current recipes
        document.querySelector('.recipes-grid').innerHTML = '';
        
        // Render filtered recipes
        recipes.forEach(recipe => {
            const recipeCard = createRecipeCard(recipe);
            document.querySelector('.recipes-grid').appendChild(recipeCard);
        });
    } catch (error) {
        console.error('Error fetching recipes:', error);
    }
}

// Function to create recipe card HTML
function createRecipeCard(recipe) {
    const card = document.createElement('div');
    card.className = 'recipe-card';
    card.dataset.category = recipe.category;
    
    card.innerHTML = `
        <div class="recipe-img">
            <img src="${recipe.image}" alt="${recipe.title}">
        </div>
        <div class="recipe-content">
            <h3 class="recipe-title">${recipe.title}</h3>
            <div class="recipe-meta">
                <span><i class="fas fa-clock"></i> ${recipe.prepTime}</span>
                <span><i class="fas fa-user"></i> ${recipe.servings} servings</span>
            </div>
            <div class="recipe-tags">
                ${recipe.tags.map(tag => `<span class="recipe-tag">${tag}</span>`).join('')}
            </div>
            <p class="recipe-description">${recipe.description}</p>
            <a href="#" class="read-more">View Recipe <i class="fas fa-arrow-right"></i></a>
        </div>
    `;
    
    return card;
}

// Initial load of recipes
document.addEventListener('DOMContentLoaded', async function() {
    try {
        const response = await fetch('/api/recipes');
        const recipes = await response.json();
        
        recipes.forEach(recipe => {
            const recipeCard = createRecipeCard(recipe);
            document.querySelector('.recipes-grid').appendChild(recipeCard);
        });
    } catch (error) {
        console.error('Error loading recipes:', error);
    }
    
    // Add event listeners
    document.getElementById('search-button').addEventListener('click', filterRecipes);
    document.getElementById('search-input').addEventListener('keyup', filterRecipes);
    
    document.querySelectorAll('.filter-btn').forEach(button => {
        button.addEventListener('click', function() {
            document.querySelectorAll('.filter-btn').forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');
            filterRecipes();
        });
    });
});