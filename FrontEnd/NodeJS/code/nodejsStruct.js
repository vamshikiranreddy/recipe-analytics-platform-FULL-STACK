const express = require('express');
const cors = require('cors');
const path = require('path');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.static('public')); // Serve static files

// Sample recipe data
const recipes = [
    {
        id: 1,
        title: "Classic Red Velvet Cake",
        category: "dessert",
        prepTime: "1 hour 30 mins",
        servings: 12,
        description: "A stunning crimson layer cake with cream cheese frosting that's moist, tender, and perfectly balanced in flavor.",
        tags: ["Dessert", "Baking"],
        image: "https://images.unsplash.com/photo-1578985545062-69928b1d9587?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 2,
        title: "Peach Crisps and Crumbles",
        category: "dessert",
        prepTime: "45 mins",
        servings: 6,
        description: "Warm, juicy peaches topped with a buttery, crunchy oat topping. Perfect served with vanilla ice cream.",
        tags: ["Dessert", "Fruit"],
        image: "https://images.unsplash.com/photo-1560138123-9db6d7bb00e7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 3,
        title: "Copycat Sweet Iced Tea",
        category: "drink",
        prepTime: "20 mins + chilling",
        servings: 8,
        description: "Refreshing Southern-style sweet tea that's perfectly balanced - not too sweet, with a hint of lemon.",
        tags: ["Drink", "Refreshing"],
        image: "https://images.unsplash.com/photo-1621506289937-a8e4df240d0b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 4,
        title: "Red Velvet Cheesecake with Oreos",
        category: "dessert",
        prepTime: "1 hour + chilling",
        servings: 10,
        description: "Decadent red velvet cheesecake with an Oreo cookie crust and creamy cream cheese topping.",
        tags: ["Dessert", "Cheesecake"],
        image: "https://images.unsplash.com/photo-1627308595173-d3bc695892ed?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 5,
        title: "Creamy Vegetable Pasta",
        category: "vegetarian",
        prepTime: "25 mins",
        servings: 4,
        description: "Creamy pasta loaded with fresh vegetables and herbs. A quick and satisfying meal for any day of the week.",
        tags: ["Main Dish", "Vegetarian"],
        image: "https://images.unsplash.com/photo-1621996346565-e3dbc353d2e5?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80"
    },
    {
        id: 6,
        title: "Mixed Berry Smoothie",
        category: "drink",
        prepTime: "5 mins",
        servings: 2,
        description: "A refreshing and nutritious smoothie packed with mixed berries, yogurt, and a touch of honey.",
        tags: ["Drink", "Breakfast"],
        image: "https://images.unsplash.com/photo-1553530666-ba11a7da3888?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80"
    }
];

// Routes
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Search endpoint
app.get('/api/recipes/search', (req, res) => {
    const { q, category } = req.query;
    let results = recipes;

    // Filter by search query
    if (q) {
        const query = q.toLowerCase();
        results = results.filter(recipe => 
            recipe.title.toLowerCase().includes(query) ||
            recipe.description.toLowerCase().includes(query) ||
            recipe.tags.some(tag => tag.toLowerCase().includes(query))
        );
    }

    // Filter by category
    if (category && category !== 'all') {
        results = results.filter(recipe => recipe.category === category);
    }

    res.json(results);
});

// Get all recipes
app.get('/api/recipes', (req, res) => {
    res.json(recipes);
});

// Get recipe by ID
app.get('/api/recipes/:id', (req, res) => {
    const id = parseInt(req.params.id);
    const recipe = recipes.find(r => r.id === id);
    
    if (!recipe) {
        return res.status(404).json({ error: 'Recipe not found' });
    }
    
    res.json(recipe);
});

// Start server
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});