import { useState } from "react";
import { MCCService } from "Frontend/generated/endpoints"; 


export default function App() {

    const [dishName, setDishName] = useState('');
    const [recipe, setRecipe] = useState('');
    const [dishImage, setDishImage] = useState('/placeholder.svg');

    const handleGenerateClick = () => {
        if (dishName) {
            MCCService.getRecipes(dishName).then(setRecipe);
            MCCService.getDishImage(dishName).then(setDishImage);
        }
    };

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-[#F0F0F0] dark:bg-[#121212] text-[#333] dark:text-[#F0F0F0] font-sans">
            <div className="w-full max-w-4xl p-6 md:p-8 lg:p-12 bg-white dark:bg-[#1A1A1A] rounded-lg shadow-md">
                <div className="flex flex-col items-center justify-center space-y-6">
                    <h1 className="text-3xl md:text-4xl font-bold tracking-tight">Moroccan Cooking Companion 🇲🇦🍽️</h1>
                    <div className="w-full flex flex-col md:flex-row items-center justify-center space-y-4 md:space-y-0 md:space-x-4">
                        <div className="flex-1">
                            <label className="block mb-2 font-medium text-gray-700 dark:text-gray-300" htmlFor="dish-name">
                                Enter the name of a Moroccan dish:
                            </label>
                            <input
                                className="w-full px-4 py-2 rounded-md border border-gray-300 dark:border-gray-600 dark:bg-[#1A1A1A] dark:text-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                                id="dish-name"
                                placeholder="e.g. Tagine, Couscous, Harira"
                                type="text"
                                onChange={e => setDishName(e.target.value)}
                            />
                        </div>
                        <button
                            className="px-6 py-2 bg-blue-500 text-white font-medium rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
                            onClick={handleGenerateClick}
                        >
                            Generate
                        </button>
                    </div>
                </div>
                <div className="mt-8 grid grid-cols-1 md:grid-cols-2 gap-8">
                    <div className="h-full">
                    <img
                        alt="Moroccan Dish"
                        className="w-full h-full object-cover rounded-lg"
                        height="400"
                        src={dishImage}
                        style={{
                            aspectRatio: "500/400",
                            objectFit: "cover",
                        }}
                        width="500"
                    />
                    </div>
                    <div className="h-full space-y-4">
                        <h2 className="text-2xl font-bold">Recipes</h2>
                        <p className="text-gray-700 dark:text-gray-300 leading-relaxed">
                            {recipe}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
}
